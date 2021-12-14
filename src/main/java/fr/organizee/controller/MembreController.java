package fr.organizee.controller;

import fr.organizee.model.Membre;
import fr.organizee.repository.MembreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/* toto */
@RestController
@CrossOrigin("*")
public class MembreController {

    @Autowired
    private MembreRepository membreRepo;

    @GetMapping("/")
    @ResponseBody
    public String home()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("<h1>Affichages des membres</h1>");
        sb.append("<ul><li><a href='http://localhost:8080/membres'>Liste des <strong>membres</strong></a></li>");
        return  sb.toString();
    }

    @GetMapping(value = "/membres")
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
}
