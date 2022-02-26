package fr.organizee.controller;

import fr.organizee.model.Evenement;
import fr.organizee.model.Menu;
import fr.organizee.repository.EvenementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/evenements")
public class EvenementController {

    @Autowired
    private EvenementRepository evenementRepo;

    // Recupérer tout les evenements pour une team {team_id}
    @GetMapping(value = "/team/{team_id}")
    public ResponseEntity<?> findByTeamId(@PathVariable int team_id){
        List<Evenement> liste = null;
        try
        {
            liste = evenementRepo.FindEvenementsByTeam(team_id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(liste);
    }

    // Ajoute un evenement au calendrier
    @PostMapping(value="/add", produces="application/json", consumes="application/json")
    public ResponseEntity<?> addTache(@RequestBody Evenement event){
        Evenement resultEvent = null;
        try {
            resultEvent = evenementRepo.saveAndFlush(event);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(resultEvent);
    }

    //Mise a jour d'un evenement par son ID
    @PutMapping("/update/{id}")
    //@PreAuthorize("hasRole('ROLE_PARENT')")
    public ResponseEntity<?> updateEvenement(@RequestBody Evenement event, @PathVariable Integer id) throws Exception {
        Evenement resultEvenement = null;
        try {
            resultEvenement = evenementRepo.save(event);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(resultEvenement);
    }

    //Efface un evenement par son ID
    @DeleteMapping(value = "/delete/{id}")
    //@PreAuthorize("hasRole('ROLE_PARENT')")
    public ResponseEntity<?> deleteEvenement(@PathVariable int id){
        try {
            evenementRepo.delete(evenementRepo.getById(id));
            return ResponseEntity.status(HttpStatus.OK).body("Evenement effacé !");

        } catch (EntityNotFoundException e) {

            return ResponseEntity.status(HttpStatus.OK).body("Evenement introuvable !");
        }
    }
}
