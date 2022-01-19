package fr.organizee.security;

import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;

import fr.organizee.exception.InvalidJWTException;
import fr.organizee.model.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * JWT : classe utilitaire chargÃ©e de fournir le Jeton (Token) et les vÃ©rifications
 */
@Component
public class JwtTokenProvider {

    // on rÃ©cupÃ¨re le secret dans notre fichier application.properties
    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;

    // ici on met la valeur par dÃ©faut
    @Value("${security.jwt.token.expire-length:3600000}")
    private long validityInMilliseconds = 3600000; // 1h pour être pénard

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Cette mÃ©thode d'initialisation s'exÃ©cute avant le constructeur
     * Elle encode notre code secret en base64 pour la transmission dans le header
     */
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    /**
     * Methode qui crÃ©e le Token avec :
     * username comme un champ "sub",
     * User Role comme champ "auth"
     * "iat" comme date du jour ,
     * "exp" as now date + validity time.
     * claims = les droits
     struture :
     HEADER : Algo + Type de Token
     {
     "alg": "HS256",
     "typ": "JWT"
     }

     PAYLOAD : data
     {
     "sub": "pbouget",
     "auth": [
     "ROLE_ADMIN",
     "ROLE_CREATOR",
     "ROLE_READER"
     ],
     "iat": 1589817421,
     "exp": 1589821021
     }

     Signature :

     Signature avec code secret :

     HMACSHA256(
     base64UrlEncode(header) + "." +
     base64UrlEncode(payload),
     03888dd6ceb88c3fee410a70802fb93d483fd52d70349d8f7e7581ae346cf658
     )

     JWT gÃ©nÃ¨rer avec cette info :
     header =   	eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.
     payload =  	eyJzdWIiOiJwYm91Z2V0IiwiYXV0aCI6WyJST0xFX0FETUlOIiwiUk9MRV9DUkVBVE9SIiwiUk9MRV9SRUFERVIiXSwiaWF0IjoxNTg5ODE3NDIxLCJleHAiOjE1ODk4MjEwMjF9.
     signature = lrKQIkrCzNMwzTN-hs_EdoYYxrb59sAlku7nmaml0vk

     vÃ©rifier sur https://jwt.io

     * @param email the user email.
     * @param roles the user roles.
     * @return the created JWT as String.
     * @throws JsonProcessingException
     */
    public String createToken(String email, List<Role> roles){

        Claims claims = Jwts.claims().setSubject(email);
        claims.put("auth", roles.stream().map(s -> new SimpleGrantedAuthority(s.getAuthority())).filter(Objects::nonNull).collect(Collectors.toList()));

        System.out.println("claims = "+claims);
        // claims = {sub=pbouget, auth=[ROLE_ADMIN, ROLE_CREATOR, ROLE_READER]}
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        String leToken = Jwts.builder()//
                .setClaims(claims)// le username avec les roles ou setPayload()
                .setIssuedAt(now)// 1589817421  pour le 18 mai 2020 Ã  17 heure 57
                .setExpiration(validity)// 1589821021 mÃªme date avec 1 heure de plus
                .signWith(SignatureAlgorithm.HS256, secretKey) // la signature avec la clef secrÃªte.
                .compact();		// concatÃ¨ne l'ensemble pour construire une chaÃ®ne
        System.out.println(leToken); // pour test cela donne ceci
  /*
   		site pour convertir une date en millisecondes : http://timestamp.fr/?
   		site structure du jeton : https://www.vaadata.com/blog/fr/jetons-jwt-et-securite-principes-et-cas-dutilisation/
   		site jwt encoder / dÃ©coder : https://jwt.io/
       eyJhbGciOiJIUzI1NiJ9.
       eyJzdWIiOiJwYm91Z2V0IiwiYXV0aCI6W3siYXV0aG9yaXR5IjoiUk9MRV9BRE1JTiJ9LHsiYXV0aG9yaXR5IjoiUk9MRV9DUkVBVE9SIn0seyJhdXRob3JpdHkiOiJST0xFX1JFQURFUiJ9XSwiaWF0IjoxNTg5ODE2OTIyLCJleHAiOjE1ODk4MjA1MjJ9.
       Cn4_UTjZ2UpJ32FVT3Bd1-VN8K62DVBHQbWiK6MNZ04

  */
        // https://www.codeflow.site/fr/article/java__how-to-convert-java-object-to-from-json-jackson

        return leToken;
    }

    /**
     * Methode qui retourne un objet Authentication basÃ© sur JWT.
     * @param token : le token pour l'authentification.
     * @return the authentication si Username est trouvÃ©.
     */
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getEmail(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    /**
     * Methode qui extrait le userName du JWT.
     * @param token : Token a analyser.
     * @return le UserName comme chaÃ®ne de caractÃ¨res.
     */
    public String getEmail(String token) {

        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    /**
     * MÃ©thode qui rÃ©cupÃ¨re la requete HTTP.
     * L'entÃªte doit contenir un champ d'autorisation ou JWT ajoute le token aprÃ¨s le mot clef Bearer.
     * @param requete : la requÃªte Ã  tester.
     * @return le JWT depuis l'entÃªte HTTP.
     */
    public String resolveToken(HttpServletRequest requeteHttp) {
        String bearerToken = requeteHttp.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    /**
     * Methode qui vï¿½rifie que JWT est valide.
     * La signature doit ï¿½tre correcte et la durï¿½e de validitï¿½ du Token doit ï¿½tre aprï¿½s "now" (maintenant)
     * @param token : Token ï¿½ valider
     * @return True si le Token est valide sinon on lance l'exception InvalidJWTException.
     * @throws InvalidJWTException
     */
    public boolean validateToken(String token) throws InvalidJWTException {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new InvalidJWTException();
        }
    }
}

