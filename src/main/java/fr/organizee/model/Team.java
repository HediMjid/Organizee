package fr.organizee.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    @OneToMany(mappedBy = "team", fetch=FetchType.LAZY)
    @JsonIgnoreProperties("team")
    private List<Membre> membres = new ArrayList<>();
    @OneToMany(mappedBy = "team", fetch=FetchType.LAZY)
    @JsonIgnoreProperties("team")
    private List<Contact> contacts = new ArrayList<>();
    @OneToMany(mappedBy = "team", fetch=FetchType.LAZY)
    @JsonIgnoreProperties("team")
    private List<TodoList> todolists = new ArrayList<>();
    @OneToMany(mappedBy = "team", fetch=FetchType.LAZY)
    @JsonIgnoreProperties("team")
    private List<Menu> menus = new ArrayList<>();

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

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return "Team [id=" + id + ", nom=" + nom + ", membre=" + membres + ", contact=" + contacts + "]";
    }
}
