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
    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="TEAM_ID")
    private Team team;
}
