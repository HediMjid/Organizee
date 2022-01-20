package fr.organizee.controller;

import fr.organizee.model.Membre;
import fr.organizee.model.Tache;
import fr.organizee.repository.MembreRepository;
import fr.organizee.repository.TacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/taches")
public class TacheController {
    @Autowired
    private TacheRepository tacheRepo;

    @GetMapping(value = "/all")
    public ResponseEntity<?> getAll(){
        List<Tache> liste = null;
        try
        {
            liste = tacheRepo.findAll();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(liste);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable int id){
        Optional<Tache> tache = null;
        try
        {
            tache = tacheRepo.findById(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(tache);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteTache(@PathVariable int id){
        try {
            tacheRepo.delete(tacheRepo.getById(id));
            return ResponseEntity.status(HttpStatus.OK).body("Tache effacée !");

        } catch (EntityNotFoundException e) {

            return ResponseEntity.status(HttpStatus.OK).body("Tache introuvable !");
        }
    }

    @PostMapping(value="/add", produces="application/json", consumes="application/json")
    public ResponseEntity<?> addTache(@RequestBody Tache tache){
        Tache resultTache = null;
        try {
            resultTache = tacheRepo.saveAndFlush(tache);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(resultTache);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTache(@RequestBody Tache tache, @PathVariable Integer id) throws Exception {
        Tache resultTache = null;
        try {
            resultTache = tacheRepo.save(tache);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(resultTache);
    }
}
