package fr.organizee.controller;

import fr.organizee.dto.JsonWebToken;
import fr.organizee.dto.MembreDto;
import fr.organizee.exception.ExistingUsernameException;
import fr.organizee.exception.InvalidCredentialsException;
import fr.organizee.model.Membre;
//import fr.organizee.model.Team;
import fr.organizee.repository.MembreRepository;
//import fr.organizee.repository.TeamRepository;
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

/* toto */
@RestController
@CrossOrigin("*")
@RequestMapping("/membres")
public class MembreController {

    @Autowired
    private MembreRepository membreRepo;

    @Autowired
    private MembreService membreService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

//    @Autowired
//    private TeamRepository teamRepo;

//    @RequestMapping("/membres")
    @ResponseBody
    public String home()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("<h1>Affichages des membres</h1>");
        sb.append("<ul><li><a href='http://localhost:8080/membres/all'>Liste des <strong>membres</strong></a></li>");
        return  sb.toString();
    }

    @GetMapping(value = "/all")
    @PreAuthorize("hasRole('ROLE_PARENT') or hasRole('ROLE_ENFANT')")
    public ResponseEntity<?> getAll(){
        List<Membre> liste = null;
        try
        {
            liste = membreRepo.findAll();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(liste);
    }

    @GetMapping("/admin/all")
    @PreAuthorize("hasRole('ROLE_PARENT')")
    public List<MembreDto> getAllAdminUsers() {
        return membreService.findAllUsers().stream().map(appUser -> new MembreDto(appUser.getEmail(), appUser.getRoleList())).collect(Collectors.toList());

    }
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


//    @GetMapping(value = "/team/all")
//    public ResponseEntity<?> getAllTeam(){
//        List<Team> liste = null;
//        try
//        {
//            liste = teamRepo.findAll();
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        }
//
//        return ResponseEntity.status(HttpStatus.OK).body(liste);
//    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_PARENT') or hasRole('ROLE_ENFANT')")
    public ResponseEntity<?> findById(@PathVariable int id){
        Optional<Membre> membre = null;
        try
        {
            membre = membreRepo.findById(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(membre);
    }

//    @GetMapping(value = "/membres/delete/{id}")
//    public void deleteMembreId(@PathVariable("id") Integer id) {
//
//            membreRepo.deleteById(id);
//
//    }

    @DeleteMapping(value = "/delete/{id}")
    @PreAuthorize("hasRole('ROLE_PARENT')")
    public ResponseEntity<?> deleteMembre(@PathVariable int id){
        try {
            membreRepo.delete(membreRepo.getById(id));
            //membreRepo.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Membre effacé !");

        } catch (EntityNotFoundException e) {

            return ResponseEntity.status(HttpStatus.OK).body("Membre introuvable !");
        }
    }

    @PostMapping("/sign-up")
    public ResponseEntity<JsonWebToken> signUp(@RequestBody Membre membre) {
        try {
            return ResponseEntity.ok(new JsonWebToken(membreService.signup(membre)));
        } catch (ExistingUsernameException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/sign-in")
    public ResponseEntity<JsonWebToken> signIn(@RequestBody Membre membre) {
        try {
            return ResponseEntity.ok(new JsonWebToken(membreService.signin(membre.getEmail(), membre.getPassword())));
        } catch (InvalidCredentialsException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ROLE_PARENT')")
    public ResponseEntity<?> updateMembre(@RequestBody Membre membre, @PathVariable Integer id) throws Exception {
        Membre resultMembre = null;
        try {
            resultMembre = membreRepo.save(membre);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(resultMembre);
    }

//    UPDATE SUR UN CHAMPS
//    @PutMapping("/update/{id}")
//    public ResponseEntity<?> updateMembre(@RequestBody Membre membre, @PathVariable Integer id) throws Exception {
//        Membre resultMembre = null;
//        Membre oldMembre = membreRepo.getById(id);
//        oldMembre.setNom(membre.getNom());
//        //Membre resultMembre = membreRepo.getById(id);
//        try {
//            resultMembre = membreRepo.save(oldMembre);
//
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        }
//
//        return ResponseEntity.status(HttpStatus.OK).body(resultMembre);
//    }

//    @GetMapping(value = "/team/{id}")
//    public ResponseEntity<?> findTeamById(@PathVariable int id){
//        Optional<Team> liste = null;
//        try
//        {
//            liste = teamRepo.findById(id);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        }
//
//        return ResponseEntity.status(HttpStatus.OK).body(liste);
//    }
}
