package fr.organizee.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String libelle;
    private LocalDate dateMenu;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="TEAM_ID")
    @JsonIgnoreProperties("menu")
    private Team team;
    @ManyToOne
    private Membre membre;

    public Menu() {
    }

    public Menu(String libelle, LocalDate dateMenu) {
        this.libelle = libelle;
        this.dateMenu = dateMenu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public LocalDate getDateMenu() {
        return dateMenu;
    }

    public void setDateMenu(LocalDate dateMenu) {
        this.dateMenu = dateMenu;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                ", dateMenu=" + dateMenu +
                '}';
    }
}
