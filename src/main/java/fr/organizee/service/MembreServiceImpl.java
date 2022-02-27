package fr.organizee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import fr.organizee.exception.ExistingUsernameException;
import fr.organizee.exception.InvalidCredentialsException;
import fr.organizee.model.Membre;
import fr.organizee.repository.MembreRepository;
import fr.organizee.security.JwtTokenProvider;

@Service
public class
MembreServiceImpl implements MembreService {

    @Autowired
    private MembreRepository membreRepository; // permet communication avec la BD

    @Autowired
    private BCryptPasswordEncoder passwordEncoder; // permet l'encodage du mot de passe

    @Autowired
    private JwtTokenProvider jwtTokenProvider;	// permet la fourniture du Jeton (Token)

    @Autowired
    private AuthenticationManager authenticationManager; // gestionnaire d'authentification


    /**
     * Permet de se connecter en encodant le mot de passe avec gÃ©nÃ©ration du token.
     */
    @Override
    public String signin(String email, String password) throws InvalidCredentialsException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            return jwtTokenProvider.createToken(email, membreRepository.findByEmail(email).get().getRoleList());
        } catch (AuthenticationException e) {
            throw new InvalidCredentialsException();
        }
    }

    @Override
    public String signup(Membre membre) throws ExistingUsernameException {
        if (!membreRepository.existsByEmail(membre.getEmail())) {
            Membre membreToSave = new Membre(membre.getNom(), membre.getPrenom(), membre.getCouleur(), membre.getDateNaissance(), membre.getTeam(), membre.getEmail(), passwordEncoder.encode(membre.getPassword()), membre.getRoleList());
            membreRepository.save(membreToSave);
            return jwtTokenProvider.createToken(membre.getEmail(), membre.getRoleList());
        } else {
            throw new ExistingUsernameException();
        }
    }

    @Override
    public List<Membre> findAllUsers() {
        return membreRepository.findAll();
    }

    @Override
    public Optional<Membre> findUserByEmail(Membre membre) {
        return this.membreRepository.findByEmail(membre.getEmail());
    }

    @Override
    public Optional<Membre> findByEmail(String email) {
        return this.membreRepository.findByEmail(email);
    }

    @Override
    public Membre chercheEmail(String email) {
        return this.membreRepository.chercheEmail(email);
    }


}

