package fr.organizee.model;

import javax.persistence.*;

@Entity
public class Tache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String texte;
    private boolean etat;
    @ManyToOne
    @JoinColumn(name = "todolist_id")
    private TodoList todolist;

    public Tache(String texte, boolean etat) {
        this.texte = texte;
        this.etat = etat;
    }

    public Tache() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Tache{" +
                "id=" + id +
                ", texte='" + texte + '\'' +
                ", etat=" + etat +
                '}';
    }
}
