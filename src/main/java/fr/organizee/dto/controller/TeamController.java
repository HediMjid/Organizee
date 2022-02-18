package fr.organizee.dto.controller;

import fr.organizee.model.Team;
import fr.organizee.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

/* toto */
@RestController
@CrossOrigin("*")
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private TeamRepository teamRepo;

    //    @RequestMapping("/teams")
    @ResponseBody
    public String home()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("<h1>Affichages des teams</h1>");
        sb.append("<ul><li><a href='http://localhost:8080/teams/all'>Liste des <strong>teams</strong></a></li>");
        return  sb.toString();
    }

    // Récupération de toutes les teams
    @GetMapping(value = "/all")
    //@PreAuthorize("hasRole('ROLE_PARENT')")
    public ResponseEntity<?> getAllTeam(){
        List<Team> liste = null;
        try
        {
            liste = teamRepo.findAll();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(liste);
    }

    @GetMapping(value = "/{id}")
    //@PreAuthorize("hasRole('ROLE_PARENT') or hasRole('ROLE_ENFANT')")
    public ResponseEntity<?> findTeamById(@PathVariable int id){
        Optional<Team> liste = null;
        try
        {
            liste = teamRepo.findById(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(liste);
    }

    @PostMapping(value="/add", produces="application/json", consumes="application/json")
    //@PreAuthorize("hasRole('ROLE_PARENT')")
    public ResponseEntity<?> addTeam(@RequestBody Team team){
        Team resultTeam = null;
        try {
            resultTeam = teamRepo.saveAndFlush(team);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(resultTeam);
    }

    @PutMapping("/update/{id}")
    //@PreAuthorize("hasRole('ROLE_PARENT')")
    public ResponseEntity<?> updateTeam(@RequestBody Team team, @PathVariable Integer id) throws Exception {
        Team resultTeam = null;
        try {
            resultTeam = teamRepo.save(team);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(resultTeam);
    }

    @DeleteMapping(value = "/delete/{id}")
    //@PreAuthorize("hasRole('ROLE_PARENT')")
    public ResponseEntity<?> deleteTeam(@PathVariable int id){
        try {
            teamRepo.delete(teamRepo.getById(id));
            //membreRepo.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Team effacée !");

        } catch (EntityNotFoundException e) {

            return ResponseEntity.status(HttpStatus.OK).body("Team introuvable !");
        }
    }
}
