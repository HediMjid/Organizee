package fr.organizee.controller;

import fr.organizee.model.Membre;
import fr.organizee.model.Team;
import fr.organizee.repository.MembreRepository;
import fr.organizee.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/* toto */
@RestController
@CrossOrigin("*")
public class MembreController {

    @Autowired
    private MembreRepository membreRepo;

    @Autowired
    private TeamRepository teamRepo;

    @GetMapping("/")
    @ResponseBody
    public String home()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("<h1>Affichages des membres</h1>");
        sb.append("<ul><li><a href='http://localhost:8080/membres/all'>Liste des <strong>membres</strong></a></li>");
        return  sb.toString();
    }

    @GetMapping(value = "/membres/all")
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

    @GetMapping(value = "/team/all")
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

    @GetMapping(value = "/membres/{id}")
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

    @GetMapping(value = "/membres/delete/{id}")
    public void deleteMembreId(@PathVariable("id") Integer id) {

            membreRepo.deleteById(id);

    }

    @GetMapping(value = "/team/{id}")
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
