package fr.organizee.model;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
public class Membre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String email;
    private String password;
    private String isAdmin;
    private String couleur;
/*    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="TEAM_ID")
    private Team team;*/
//    @ManyToOne
//    private Smiley smiley;

    public Membre() {
    }

    public Membre(String nom, String prenom, LocalDate dateNaissance, String email, String password, String isAdmin, String couleur, Team team) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
        this.couleur = couleur;
/*        this.team = team;*/
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
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public LocalDate getDateNaissance() {
        return dateNaissance;
    }
    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getIsAdmin() {
        return isAdmin;
    }
    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }
 /*   public Team getTeam() {
        return team;
    }
    public void setTeam(Team team) {
        this.team = team;
    }*/

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    @Override
    public String toString() {
        return "Membre{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", dateNaissance=" + dateNaissance +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin='" + isAdmin + '\'' +
                ", couleur='" + couleur + '\'' +
                /*", team=" + team +*/
                '}';
    }
}

