package fr.organizee.controller;

import fr.organizee.model.Evenement;
import fr.organizee.model.Membre;
import fr.organizee.model.Team;
import fr.organizee.repository.EvenementRepository;
import fr.organizee.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/evenements")
public class EvenementController {

    @Autowired
    private EvenementRepository evenementRepo;

    // Recupérer tout les evenements pour une team {id}
    @GetMapping(value = "/all/{id}")
    public ResponseEntity<?> getAll(){
        List<Evenement> liste = null;
        try
        {
            liste = evenementRepo.findAll();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(liste);
    }
}
