package fr.organizee.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TodoList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    @OneToMany(mappedBy = "todolist")
    private List<Tache> taches = new ArrayList<>();

    public TodoList() {
    }

    public TodoList(String nom) {
        this.nom = nom;
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

    @Override
    public String toString() {
        return "TodoList{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}
