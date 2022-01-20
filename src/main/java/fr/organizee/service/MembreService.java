package fr.organizee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.organizee.exception.ExistingUsernameException;
import fr.organizee.exception.InvalidCredentialsException;
import fr.organizee.model.Membre;

@Service
public interface MembreService {

    /**
     * Methode qui permet Ã  un utilisateur de se connecter.
     * @param email : nom de l'utilisateur.
     * @param password : mot de passe de l'utilisateur.
     * @returnun JWT si credentials est valide, throws InvalidCredentialsException otherwise.
     * @throws InvalidCredentialsException
     */
    String signin(String email, String password) throws InvalidCredentialsException;

    /**
     * Methode qui permet de s'inscrire.
     * @param membre nouvel utilisateur.
     * @return un JWT si user n'existe pas dÃ©jÃ  !
     * @throws ExistingUsernameException
     */
    String signup(Membre membre) throws ExistingUsernameException;


    /**
     * Methode qui retourne tous les utilisateurs de la bd
     * @return the list of all application users.
     */
    List<Membre> findAllUsers();

    /**
     * Methode qui retourne un utilisateur Ã  partir de son username
     * @param email the username to look for.
     * @return an Optional object containing user if found, empty otherwise.
     */
    Optional<Membre> findUserByEmail(Membre membre);
    Optional<Membre> findByEmail(String email);

    Membre chercheEmail(String email);

}

