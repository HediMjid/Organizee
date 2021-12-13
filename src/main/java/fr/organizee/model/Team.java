package fr.organizee.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    @OneToMany
    private List<Membre> membres = new ArrayList<>();
    @OneToMany
    @JoinTable(name = "repertoire")
    private List<Contact> contacts = new ArrayList<>();
    @OneToMany
    @JoinTable(name = "team_todolist")
    private List<TodoList> todolists = new ArrayList<>();

    public Team() {
        super();
    }

    public Team(int id, String nom, List<Membre> membre) {
        super();
        this.id = id;
        this.nom = nom;
        this.membres = membre;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public List<Membre> getMembre() {
        return membres;
    }
    public void setMembre(List<Membre> membre) {
        this.membres = membre;
    }

    @Override
    public String toString() {
        return "Team [id=" + id + ", nom=" + nom + ", membre=" + membres + "]";
    }
}
