package fr.organizee.controller;

import fr.organizee.model.TodoList;
import fr.organizee.model.Team;
import fr.organizee.repository.TodoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/todolist")
public class TodoListController {

    @Autowired
    private TodoListRepository todolistRepo;


    @GetMapping(value = "/all")
    public ResponseEntity<?> getAll(){
        List<TodoList> liste = null;
        try
        {
            liste = todolistRepo.findAll();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(liste);
    }

  //Ajoute une nouvelle ToDoList
    @PostMapping(value="/add/{team_id}", produces="application/json", consumes= "application/json")
    //@PreAuthorize("hasRole('ROLE_PARENT') or hasRole('ROLE_ENFANT')")
    public ResponseEntity<?> addTodolist(@RequestBody TodoList todolist, @PathVariable Integer team_id) {
         TodoList resultTodolist = null;
        try {
            Team team = new Team();
            team.setId(team_id);
            todolist.setTeam(team);
            resultTodolist = todolistRepo.saveAndFlush(todolist);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(resultTodolist);
    }

    @DeleteMapping(value = "/delete/{id}")
    //@PreAuthorize("hasRole('ROLE_PARENT')")
    public ResponseEntity<?> deleteTodolist(@PathVariable int id){
        try {
            todolistRepo.delete(todolistRepo.getById(id));
            //membreRepo.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Todolist effacée !");

        } catch (EntityNotFoundException e) {

            return ResponseEntity.status(HttpStatus.OK).body("Todolist introuvable !");
        }
    }

    @GetMapping(value = "/team/{team_id}")
    public ResponseEntity<?> findByTeamId(@PathVariable int team_id){
        List<TodoList> todoLists = null;
        try
        {
            todoLists = todolistRepo.FindTodolistByTeam(team_id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(todoLists);
    }

    //Met a jour les informations d'une date avec son ID
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTodolist(@RequestBody TodoList todolist, @PathVariable Integer id) throws Exception {
        TodoList resultTodolist = null;
        try {
            resultTodolist = todolistRepo.save(todolist);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(resultTodolist);
    }
}
