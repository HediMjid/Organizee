package fr.organizee.service;

import fr.organizee.model.Mail;

import javax.mail.MessagingException;

public interface SendMailService {
    void sendMail(Mail mail);

    void sendMailHTML(Mail mail) throws MessagingException;

    void sendMailWithAttachments(Mail mail) throws MessagingException;
}


