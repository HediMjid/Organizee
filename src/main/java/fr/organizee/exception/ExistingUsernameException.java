package fr.organizee.exception;

/**
 * Classe personnalisÃ©e pour gÃ©rer un message si l'utilisateur (User) existe en Base de donnÃ©es
 */
public class ExistingUsernameException extends Exception {

    private static final long serialVersionUID = 1L;

    @Override
    public String getMessage()
    {
        return "Désolé, l'utilisateur existe déjà en base de données !";
    }
}
