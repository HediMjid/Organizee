package fr.organizee.dto.controller;

import fr.organizee.model.Tache;
import fr.organizee.model.TodoList;
import fr.organizee.repository.TacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    // Récupère toutes les taches de toutes la base toutes team confondu
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

    // Récupère les infos d'une tache avec son ID
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

    // Efface une tache avec son ID
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteTache(@PathVariable int id){
        try {
            tacheRepo.delete(tacheRepo.getById(id));
            return ResponseEntity.status(HttpStatus.OK).body("Tache effacée !");

        } catch (EntityNotFoundException e) {

            return ResponseEntity.status(HttpStatus.OK).body("Tache introuvable !");
        }
    }

    // Ajoute une tache
    @PostMapping(value="/add/{idTodoList}", produces="application/json", consumes="application/json")
    public ResponseEntity<?> addTache(@RequestBody Tache tache,@PathVariable Integer idTodoList){
        Tache resultTache = null;
        try {
            TodoList todolist=new TodoList();
            todolist.setId(idTodoList);
            tache.setTodolist(todolist);
            resultTache = tacheRepo.saveAndFlush(tache);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(resultTache);
    }

    //Met a jour les informations d'une tache avec son ID
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTache(@RequestBody Tache tache, @PathVariable Integer id) throws Exception {
        Tache resultTache = null;
        try {
            TodoList todolist=new TodoList();
            todolist.setId(tacheRepo.findById(tache.getId()).get().getTodolist().getId());
            tache.setTodolist(todolist);
            resultTache = tacheRepo.save(tache);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(resultTache);
    }

    //A revoir, résultat a chier, passez par la todolist
    @GetMapping(value = "team/{team_id}")
    public ResponseEntity<?> findByTeamId(@PathVariable int team_id){
        List<Tache> taches = null;
        try
        {
            taches = tacheRepo.FindTachesByTeam(team_id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(taches);
    }
}
