package fr.organizee.dto.controller;

import fr.organizee.model.Menu;
import fr.organizee.model.Team;
import fr.organizee.repository.MenuRepository;
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
@RequestMapping("/menus")
public class MenuController {

    @Autowired
    private MenuRepository menuRepository;

    //Récupère les infos d'un menu par son ID
    @GetMapping(value = "/{id}")
    //@PreAuthorize("hasRole('ROLE_PARENT') or hasRole('ROLE_ENFANT')")
    public ResponseEntity<?> findById(@PathVariable int id){
        Optional<Menu> menu = null;
        try
        {
            menu = menuRepository.findById(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(menu);
    }

    //Récupère les infos des menus par la team ID
    @GetMapping(value = "team/{team_id}")
    //@PreAuthorize("hasRole('ROLE_PARENT') or hasRole('ROLE_ENFANT')")
    public ResponseEntity<?> findByTeamId(@PathVariable int team_id) {
        List<Menu> menus = null;
        try {
            menus = menuRepository.FindMenusByTeam(team_id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(menus);
    }

    //Ajoute un nouveau menu
    @PostMapping(value="/add/{team_id}", produces="application/json", consumes= "application/json")
    //@PreAuthorize("hasRole('ROLE_PARENT') or hasRole('ROLE_ENFANT')")
    public ResponseEntity<?> addMenu(@RequestBody Menu menu, @PathVariable Integer team_id){
        Menu resultMenu = null;
        try {
            Team team=new Team();
            team.setId(team_id);
            menu.setTeam(team);
            resultMenu = menuRepository.saveAndFlush(menu);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(resultMenu);
    }

    //Mise a jour d'un menu par son ID
    @PutMapping("/update/{id}")
    //@PreAuthorize("hasRole('ROLE_PARENT')")
    public ResponseEntity<?> updateMenu(@RequestBody Menu menu, @PathVariable Integer id) throws Exception {
        Menu resultMenu = null;
        try {
            resultMenu = menuRepository.save(menu);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(resultMenu);
    }

    //Efface un menu par son ID
    @DeleteMapping(value = "/delete/{id}")
    //@PreAuthorize("hasRole('ROLE_PARENT')")
    public ResponseEntity<?> deleteMenu(@PathVariable int id){
        try {
            menuRepository.delete(menuRepository.getById(id));
            return ResponseEntity.status(HttpStatus.OK).body("Menu effacé !");

        } catch (EntityNotFoundException e) {

            return ResponseEntity.status(HttpStatus.OK).body("Menu introuvable !");
        }
    }
}

