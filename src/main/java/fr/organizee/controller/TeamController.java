package fr.organizee.controller;

import fr.organizee.model.Team;
import fr.organizee.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(value = "/all")
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

}
