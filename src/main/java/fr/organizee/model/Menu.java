package fr.organizee.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate dateMenu;
    private String repasMidi;
    private String repasSoir;
    @ManyToOne
    @JoinColumn(name="TEAM_ID")
    @JsonIgnoreProperties({"menu"})
    private Team team;

    public Menu() {
    }

    public Menu(LocalDate dateMenu,String repasMidi, String repasSoir, Team team) {
        this.dateMenu = dateMenu;
        this.repasMidi= repasMidi;
        this.repasSoir= repasSoir;
        this.team = team;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateMenu() {
        return dateMenu;
    }

    public void setDateMenu(LocalDate dateMenu) {
        this.dateMenu = dateMenu;
    }

    public String getRepasMidi() {
        return repasMidi;
    }

    public void setRepasMidi(String repasMidi) {
        this.repasMidi = repasMidi;
    }

    public String getRepasSoir() {
        return repasSoir;
    }

    public void setRepasSoir(String repasSoir) {
        this.repasSoir = repasSoir;
    }

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
                ", dateMenu=" + dateMenu +
                ", repasMidi='" + repasMidi + '\'' +
                ", repasSoir='" + repasSoir + '\'' +
                ", team=" + team +
                '}';
    }
}
