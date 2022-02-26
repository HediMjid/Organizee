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
    private LocalDateTime start;
    private LocalDateTime end;
    private int allDay;
    private String text;
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

    public Evenement(int id, LocalDateTime start, LocalDateTime end, int allDay, String text, Membre membre, Team team) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.allDay = allDay;
        this.text = text;
        this.membre = membre;
        this.team = team;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public int getAllDay() {
        return allDay;
    }

    public void setAllDay(int allDay) {
        this.allDay = allDay;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
                ", start=" + start +
                ", end=" + end +
                ", allDay=" + allDay +
                ", text='" + text + '\'' +
                ", membre=" + membre +
                ", team=" + team +
                '}';
    }
}
