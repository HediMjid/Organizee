package fr.organizee.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Smiley {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String smileyUrl;
    @OneToMany
    private List<Membre> membres = new ArrayList<>();

    public Smiley() {
    }

    public Smiley(String smileyUrl) {
        this.smileyUrl = smileyUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSmileyUrl() {
        return smileyUrl;
    }

    public void setSmileyUrl(String smileyUrl) {
        this.smileyUrl = smileyUrl;
    }

    @Override
    public String toString() {
        return "Smiley{" +
                "id=" + id +
                ", smileyUrl='" + smileyUrl + '\'' +
                '}';
    }
}
