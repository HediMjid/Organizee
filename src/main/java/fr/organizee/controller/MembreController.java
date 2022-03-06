package fr.organizee.controller;

import fr.organizee.dto.JsonWebToken;
import fr.organizee.dto.MembreDto;
import fr.organizee.exception.ExistingUsernameException;
import fr.organizee.exception.InvalidCredentialsException;
import fr.organizee.exception.MembreNotFoundException;
import fr.organizee.model.Membre;
import fr.organizee.model.Team;
import fr.organizee.repository.MembreRepository;
import fr.organizee.service.MembreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@CrossOrigin(origins = "*")
@RequestMapping(value = "/membres")
@RestController
public class MembreController {

    private MembreRepository membreRepository;
    private MembreService membreService;
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * Contrôleur Membre
     */
    public MembreController(MembreRepository membreRepository,
                            MembreService membreService,
                            BCryptPasswordEncoder passwordEncoder) {
        this.membreRepository = membreRepository;
        this.membreService = membreService;
        this.passwordEncoder = passwordEncoder;

    }


    /**
     * Rechercher tous les membres
     *
     * @return http://localhost:8088/membres/all
     */
    @GetMapping(value = "/all")
    //@PreAuthorize("hasRole('ROLE_PARENT') or hasRole('ROLE_ENFANT')")
    public ResponseEntity<?> getAllMembres() {
        List<Membre> listeMembres;
        try {
            listeMembres = membreRepository.findAll();
        } catch (Exception e) {
            MembreNotFoundException commandeNotFoundException = new MembreNotFoundException();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(commandeNotFoundException.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(listeMembres);
    }

    /**
     * Rechercher tous les membres admin
     *
     * @return http://localhost:8088/membres/admin/all
     */

    @GetMapping(value = "/admin/all")
    @PreAuthorize("hasRole('ROLE_PARENT')")
    public List<MembreDto> getAllAdminUsers() {
        return membreService.findAllUsers().stream().map(appUser ->
                new MembreDto(appUser.getEmail(),
                        appUser.getRoleList())).collect(Collectors.toList());
    }

    /**
     * Rechercher un membre par son Id
     *
     * @return http://localhost:8088/membres/1
     */

    @GetMapping(value = "/{id}")
    //@PreAuthorize("hasRole('ROLE_PARENT') or hasRole('ROLE_ENFANT')")
    public ResponseEntity<?> getMembreById(@PathVariable int id) {
        Optional<Membre> membre;
        try {
            membre = membreRepository.findById(id);
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
     * Rechercher les membres par l'Id de leur team
     *
     * @return http://localhost:8088/membres/team/1
     */
    @GetMapping(value = "team/{team_id}")
    //@PreAuthorize("hasRole('ROLE_PARENT') or hasRole('ROLE_ENFANT')")
    public ResponseEntity<?> findByTeamId(@PathVariable int team_id) {
        List<Membre> membres = null;
        try {
            membres = membreRepository.FindMembresByTeam(team_id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(membres);
    }

    /**
     * Inscription
     *
     * @return http://localhost:8088/membres/sign-up
     */

    @PostMapping(value = "/sign-up")
    public ResponseEntity<JsonWebToken> signUp(@RequestBody Membre membre) {
        try {
            return ResponseEntity.ok(new JsonWebToken(membreService.signup(membre)));
        } catch (ExistingUsernameException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Ajout d'un membre
     *
     * @return http://localhost:8088/membres/add/1
     */
    @PostMapping(value = "/add/{team_id}", produces = "application/json", consumes = "application/json")
    //@PreAuthorize("hasRole('ROLE_PARENT') or hasRole('ROLE_ENFANT')")
    public ResponseEntity<?> addMembre(@RequestBody Membre membre, @PathVariable Integer team_id) {
        Membre resultMembre = null;
        try {
            Team team = new Team();
            team.setId(team_id);
            membre.setTeam(team);
            //resultMembre = membreRepository.saveAndFlush(membre);
            return ResponseEntity.ok(new JsonWebToken(membreService.signup(membre)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

        //return ResponseEntity.status(HttpStatus.CREATED).body(resultMembre);
    }

    /**
     * Modifier un membre par son Id
     *
     * @return http://localhost:8088/membres/update/1
     */
    @PutMapping("/update/{team_id}/{id}")
    //@PreAuthorize("hasRole('ROLE_PARENT')")
    public ResponseEntity<?> updateMembre(@RequestBody Membre membre, @PathVariable int id, @PathVariable int team_id) {
        Optional<Membre> membreUpdate;
        try {
            membreUpdate = membreRepository.findById(id);
            // membre trouvé
            if (membreUpdate.isPresent()) {
                membre.setId(membreRepository.findById(id).get().getId());
                Team team = new Team();
                team.setId(team_id);
                membre.setTeam(team);
                membreRepository.save(membre);
            }
            //membre inconnu
            else {
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
     *
     * @return http://localhost:8088/membres/delete/1
     */
    @DeleteMapping(value = "/delete/{id}")
    //@PreAuthorize("hasRole('ROLE_PARENT')")
    public ResponseEntity<?> deleteMembre(@PathVariable int id) {
        try {
            membreRepository.getById(id);
            membreRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Membre supprimé !");
        } catch (Exception e) {
            MembreNotFoundException membreNotFoundException = new MembreNotFoundException(id);
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
    @PostMapping("/forgot-password")
    public ResponseEntity<?> findUserByEmail(@RequestBody Membre membre) {
        Membre resultMembre = null;
        try {
            resultMembre = membreRepository.chercheEmail(membre.getEmail());
            String uuid = UUID.randomUUID().toString();
            resultMembre.setPassword(uuid);
            membreRepository.saveAndFlush(resultMembre);
            return ResponseEntity.status(HttpStatus.OK).body(uuid);
        } catch (EntityNotFoundException e) {

            return ResponseEntity.status(HttpStatus.OK).body("Email introuvable !");
        }

    }

    @PutMapping("/reset-password/{uuid}")
    public ResponseEntity<?> updatePassword(@RequestBody Membre membre, @PathVariable String uuid) throws Exception {
        Membre resultMembre = null;
        try {
            resultMembre = membreRepository.findByUUID(uuid);
            resultMembre.setPassword(passwordEncoder.encode(membre.getPassword()));
            membreRepository.saveAndFlush(resultMembre);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(resultMembre);
    }

    @PutMapping("/update/smiley/{idUser}")
    public ResponseEntity<?> updateSmiley(@RequestBody String numero, @PathVariable int idUser) throws Exception {
        Optional <Membre> resultMembre ;
        try {
            resultMembre = membreRepository.findById(idUser);
            resultMembre.get().setSmiley(numero);
            membreRepository.saveAndFlush(resultMembre.get());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(resultMembre);
    }
    }


