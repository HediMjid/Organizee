package fr.organizee.dto.controller;

import fr.organizee.model.Contact;
import fr.organizee.repository.ContactRepository;
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
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactRepository contactRepo;

    //Récupère les infos d'un contact par son ID
    @GetMapping(value = "/{id}")
    //@PreAuthorize("hasRole('ROLE_PARENT') or hasRole('ROLE_ENFANT')")
    public ResponseEntity<?> findById(@PathVariable int id){
        Optional<Contact> contact = null;
        try
        {
            contact = contactRepo.findById(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(contact);
    }

    //Récupère les infos d'un contact par la team ID
    @GetMapping(value = "team/{team_id}")
    //@PreAuthorize("hasRole('ROLE_PARENT') or hasRole('ROLE_ENFANT')")
    public ResponseEntity<?> findByTeamId(@PathVariable int team_id){
        List<Contact> contacts = null;
        try
        {
            contacts = contactRepo.FindContactsByTeam(team_id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(contacts);
    }

    //Ajoute un nouveau contact
    @PostMapping(value="/add")
    //@PreAuthorize("hasRole('ROLE_PARENT') or hasRole('ROLE_ENFANT')")
    public ResponseEntity<?> addContact(@RequestBody Contact contact){
        Contact resultContact = null;
        try {
            resultContact = contactRepo.saveAndFlush(contact);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(resultContact);
    }

    //Mise à jour du contact par son ID
    @PutMapping("/update/{id}")
    //@PreAuthorize("hasRole('ROLE_PARENT') or hasRole('ROLE_ENFANT')")
    public ResponseEntity<?> updateContact(@RequestBody Contact contact, @PathVariable Integer id) throws Exception {
        Contact resultContact = null;
        try {
            resultContact = contactRepo.save(contact);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(resultContact);
    }

    //Efface le contact par on ID
    @DeleteMapping(value = "/delete/{id}")
    //@PreAuthorize("hasRole('ROLE_PARENT')")
    public ResponseEntity<?> deleteContact(@PathVariable int id){
        try {
            contactRepo.delete(contactRepo.getById(id));
            return ResponseEntity.status(HttpStatus.OK).body("Contact effacé !");

        } catch (EntityNotFoundException e) {

            return ResponseEntity.status(HttpStatus.OK).body("Contact introuvable !");
        }
    }
}
