package fr.organizee.controller;

import fr.organizee.model.Team;
import fr.organizee.repository.TeamRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "*")
@RequestMapping("/teams")
@RestController
public class TeamController {

    private TeamRepository teamRepository;

    /**
     * Contrôleur Team
     */
    public TeamController(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }


    /**
     * Rechercher toutes les teams
     *
     * @return http://localhost:8088/teams/all
     */
    @GetMapping(value = "/all")
    //@PreAuthorize("hasRole('ROLE_PARENT')")
    public ResponseEntity<?> getAllTeam() {
        List<Team> liste = null;
        try {
            liste = teamRepository.findAll();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(liste);
    }


    /**
     * Rechercher une team par son Id
     *
     * @return http://localhost:8088/teams/1
     */
    @GetMapping(value = "/{id}")
    //@PreAuthorize("hasRole('ROLE_PARENT') or hasRole('ROLE_ENFANT')")
    public ResponseEntity<?> findTeamById(@PathVariable int id) {
        Optional<Team> liste = null;
        try {
            liste = teamRepository.findById(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(liste);
    }


    /**
     * Création d'une team
     *
     * @return http://localhost:8088/teams/add
     */
    @PostMapping(value = "/add", produces = "application/json", consumes = "application/json")
    //@PreAuthorize("hasRole('ROLE_PARENT')")
    public ResponseEntity<?> addTeam(@RequestBody Team team) {
        Team resultTeam = null;
        try {
            resultTeam = teamRepository.saveAndFlush(team);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(resultTeam);
    }

    /**
     * Modifier une teamm par son Id
     *
     * @return http://localhost:8088/teams/update/1
     */
    @PutMapping("/update/{id}")
    //@PreAuthorize("hasRole('ROLE_PARENT')")
    public ResponseEntity<?> updateTeam(@RequestBody Team team, @PathVariable Integer id) throws Exception {
        Team resultTeam = null;
        try {
            resultTeam = teamRepository.save(team);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(resultTeam);
    }

    /**
     * Supprimer une team par son Id
     *
     * @return http://localhost:8088/teams/delete/1
     */
    @DeleteMapping(value = "/delete/{id}")
    //@PreAuthorize("hasRole('ROLE_PARENT')")
    public ResponseEntity<?> deleteTeam(@PathVariable int id) {
        try {
            teamRepository.delete(teamRepository.getById(id));
            //membreRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Team effacée !");

        } catch (EntityNotFoundException e) {

            return ResponseEntity.status(HttpStatus.OK).body("Team introuvable !");
        }
    }
}
