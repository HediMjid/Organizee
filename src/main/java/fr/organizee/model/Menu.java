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
    private String repas;
   // private int validationProposition;
    @ManyToOne
    @JoinColumn(name="TEAM_ID")
    @JsonIgnoreProperties({"menu","membre"})
    private Team team;

    public Menu() {
    }

    public Menu(String libelle, LocalDate dateMenu,String repas, Team team) {
        this.libelle = libelle;
        this.dateMenu = dateMenu;
        this.repas= repas;
        this.team = team;
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

    public String getRepas() {
        return repas;
    }

    public void setRepas(String repas) {
        this.repas = repas;
    }

   // public int getValidationProposition() {
     //   return validationProposition;
    //}

    //public void setValidationProposition(int validationProposition) {
     //   this.validationProposition = validationProposition;
   // }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                ", dateMenu=" + dateMenu +
                ", team=" + team +
                ", repas=" + repas +
                '}';
    }
}
