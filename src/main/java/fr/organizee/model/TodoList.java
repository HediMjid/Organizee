package fr.organizee.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @ManyToOne
    @JoinColumn(name="TEAM_ID")
    @JsonIgnoreProperties({"todolist","membre"})
    private Team team;
    @OneToMany(mappedBy = "todolist", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties("todolist")
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

    public List<Tache> getTaches() {
        return taches;
    }

    public void setTaches(List<Tache> taches) {
        this.taches = taches;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "TodoList{" +
                "id=" + id +
                ", nom='" + nom + ", taches='" + taches + "}";
    }
}
