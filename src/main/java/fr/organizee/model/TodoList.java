package fr.organizee.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TodoList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="TEAM_ID")
    @JsonIgnoreProperties("todolist")
    private Team team;

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
