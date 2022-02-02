package fr.organizee.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Evenement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime eventDebut;
    private LocalDateTime eventFin;
    private int allDay;
    private String libelle;
    @ManyToOne
    @JoinColumn(name="MEMBRE_ID")
    @JsonIgnoreProperties("evenement")
    private Membre membre;
    @ManyToOne
    @JoinColumn(name="TEAM_ID")
    @JsonIgnoreProperties({"evenement", "membre"})
    private Team team;

    public Evenement() {
    }

    public Evenement(int id, LocalDateTime eventDebut, LocalDateTime eventFin, int allDay, String libelle, Membre membre, Team team) {
        this.id = id;
        this.eventDebut = eventDebut;
        this.eventFin = eventFin;
        this.allDay = allDay;
        this.libelle = libelle;
        this.membre = membre;
        this.team = team;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getEventDebut() {
        return eventDebut;
    }

    public void setEventDebut(LocalDateTime eventDebut) {
        this.eventDebut = eventDebut;
    }

    public LocalDateTime getEventFin() {
        return eventFin;
    }

    public void setEventFin(LocalDateTime eventFin) {
        this.eventFin = eventFin;
    }

    public int getAllDay() {
        return allDay;
    }

    public void setAllDay(int allDay) {
        this.allDay = allDay;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Evenement{" +
                "id=" + id +
                ", eventDebut=" + eventDebut +
                ", eventFin=" + eventFin +
                ", allDay=" + allDay +
                ", libelle='" + libelle + '\'' +
                ", membre=" + membre +
                ", team=" + team +
                '}';
    }
}
