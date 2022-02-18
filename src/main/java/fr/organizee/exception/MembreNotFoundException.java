package fr.organizee.exception;


public class MembreNotFoundException  extends RuntimeException{
    public MembreNotFoundException(int id){ super("Membre" + id + " introuvable");}
    public MembreNotFoundException(){ super("Membres introuvables");}
}
