package fr.organizee.controller;

import fr.organizee.dto.JsonWebToken;
import fr.organizee.dto.MembreDto;
import fr.organizee.exception.ExistingUsernameException;
import fr.organizee.exception.InvalidCredentialsException;
import fr.organizee.exception.MembreNotFoundException;
import fr.organizee.model.Membre;
import fr.organizee.repository.MembreRepository;
import fr.organizee.service.MembreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@CrossOrigin("*")
@RequestMapping(value="/membres")
public class MembreController {

    @Autowired
    private MembreRepository membreRepo;
    @Autowired
    private MembreService membreService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public MembreController() {}

    @ResponseBody
    public String home()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("<h1>Affichages des membres</h1>");
        sb.append("<ul><li><a href='http://localhost:8080/membres/all'>Liste des <strong>membres</strong></a></li>");
        return  sb.toString();
    }

    /**
     * Rechercher tous les membres
     * @return
     * http://localhost:8088/membres/all
     */
    @GetMapping(value = "/all")
    //@PreAuthorize("hasRole('ROLE_PARENT') or hasRole('ROLE_ENFANT')")
    public ResponseEntity<?> getAllMembres(){
        List<Membre> listeMembres;
        try
        {
            listeMembres = membreRepo.findAll();
        } catch (Exception e) {
            MembreNotFoundException commandeNotFoundException = new MembreNotFoundException();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(commandeNotFoundException.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(listeMembres);
    }

    /**
     * Rechercher tous les membres admin
     * @return
     * http://localhost:8088/membres/admin/all
     */

    @GetMapping(value="/admin/all")
    @PreAuthorize("hasRole('ROLE_PARENT')")
    public List<MembreDto> getAllAdminUsers() {
        return membreService.findAllUsers().stream().map(appUser ->
                new MembreDto(appUser.getEmail(),
                        appUser.getRoleList())).collect(Collectors.toList());
    }

    /**
     * Rechercher un membre par son Id
     * @return
     * http://localhost:8088/membres/1
     */

    @GetMapping(value="/{id}")
    //@PreAuthorize("hasRole('ROLE_PARENT') or hasRole('ROLE_ENFANT')")
    public ResponseEntity<?> getMembreById(@PathVariable int id) {
        Optional<Membre> membre;
        try {
            membre = membreRepo.findById(id);
            if (membre.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(membre);
            } else {
                MembreNotFoundException membreNotFoundException = new MembreNotFoundException(id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(membreNotFoundException.getMessage());
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Ajouter un membre et inscription
     * @return
     * http://localhost:8088/membres/sign-up
     */

    @PostMapping(value="/sign-up")
    public ResponseEntity<JsonWebToken> signUp(@RequestBody Membre membre) {
        try {
            return ResponseEntity.ok(new JsonWebToken(membreService.signup(membre)));
        } catch (ExistingUsernameException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Modifier une commande par son Id
     * @return
     * http://localhost:8088/membres/update/1
     *
     */
    @PutMapping("/update/{id}")
    //@PreAuthorize("hasRole('ROLE_PARENT')")
    public ResponseEntity<?> updateMembre(@RequestBody Membre membre, @PathVariable int id){
        Optional<Membre> membreUpdate;
        try {
            membreUpdate = membreRepo.findById(id);
            // membre trouvé
            if(membreUpdate.isPresent()){
                membre.setId(membreUpdate.get().getId());
                membreRepo.save(membre);
            }
            //membre inconnu
            else{
                MembreNotFoundException membreNotFoundException = new MembreNotFoundException(id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(membreNotFoundException.getMessage());
            }
            //gestion de l'erreur
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        // retour membre
        return ResponseEntity.status(HttpStatus.OK).body(membre);
    }

    /**
     * Supprimer un membre par son Id
     * @return
     *  http://localhost:8088/membres/delete/1
     */
    @DeleteMapping(value = "/delete/{id}")
    //@PreAuthorize("hasRole('ROLE_PARENT')")
    public ResponseEntity<?> deleteMembre(@PathVariable int id){
        try {
            membreRepo.getById(id);
            membreRepo.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Membre supprimé !");
        } catch (Exception e) {
            MembreNotFoundException membreNotFoundException = new membreNotFoundException(id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(membreNotFoundException.getMessage());
        }
    }


    /**************************** Login / Se connceter ****************************/
    @PostMapping("/sign-in")
    public ResponseEntity<JsonWebToken> signIn(@RequestBody Membre membre) {
        try {
            return ResponseEntity.ok(new JsonWebToken(membreService.signin(membre.getEmail(), membre.getPassword())));
        } catch (InvalidCredentialsException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    /********************* Gestion Mot de Passe ************************************/


    //cette methode ne fonctionne pas parce que ça affiche "trouvé" dans tous les cas
    @GetMapping("/forgot-password")
    //@PreAuthorize("hasRole('ROLE_PARENT') or hasRole('ROLE_ENFANT')")
    public ResponseEntity<?> findUserByEmail(@RequestBody Membre findUserByEmail) {

        try {
            this.membreService.findUserByEmail(findUserByEmail);
            return ResponseEntity.status(HttpStatus.OK).body("Email trouvé !");

        } catch (EntityNotFoundException e) {

            return ResponseEntity.status(HttpStatus.OK).body("Email introuvable !");
        }

    }

    @PutMapping("/reset-password/{email}")
    //@PreAuthorize("hasRole('ROLE_PARENT') or hasRole('ROLE_ENFANT')")
    public ResponseEntity<?> updatePassword(@RequestBody String password, @PathVariable String email) throws Exception {
        Membre resultMembre;
        try {
            resultMembre = this.membreService.chercheEmail(email);

            System.out.println(resultMembre);

            resultMembre.setPassword(passwordEncoder.encode(password));

            System.out.println(password);

            this.membreRepo.save(resultMembre);
            System.out.println(resultMembre.getPassword());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(resultMembre);
    }



}
