package fr.organizee.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
public class Tache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String texte;
    private Boolean etat;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="TODOLIST_ID")
    @JsonIgnoreProperties("tache")
    private TodoList todolist;

    public Tache(String texte, Boolean etat) {
        this.texte = texte;
        this.etat = etat;
    }

    public Tache() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }

    public TodoList getTodolist() {
        return todolist;
    }

    public void setTodolist(TodoList todolist) {
        this.todolist = todolist;
    }

    @Override
    public String toString() {
        return "Tache{" +
                "id=" + id +
                ", texte='" + texte + '\'' +
                ", etat=" + etat +
                '}';
    }
}
