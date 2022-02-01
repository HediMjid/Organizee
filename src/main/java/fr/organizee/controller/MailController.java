package fr.organizee.controller;

import fr.organizee.model.Mail;
import fr.organizee.service.SendMailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/sendmail")
public class MailController {
    SendMailService service;

    public MailController(SendMailService service) {
        this.service = service;
    }

    // Envoi de mail en text brut
    @PostMapping("/text")
    public ResponseEntity<String> sendMail(@RequestBody Mail mail) {
        service.sendMail(mail);
        return new ResponseEntity<>("Email Sent successfully", HttpStatus.OK);
    }

    // Envoi de mail au format HTML
    @PostMapping("/html")
    public ResponseEntity<String> sendMailHTML(@RequestBody Mail mail) throws MessagingException {
        service.sendMailHTML(mail);
        return new ResponseEntity<>("HTML mail sent successfully", HttpStatus.OK);
    }

    // Envoi du mail avec une piece jointe
    @PostMapping("/attachment")
    public ResponseEntity<String> sendAttachmentEmail(@RequestBody Mail mail) throws MessagingException {
        service.sendMailWithAttachments(mail);
        return new ResponseEntity<>("Attachment mail sent successfully", HttpStatus.OK);
    }
}