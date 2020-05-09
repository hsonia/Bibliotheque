package com.example.bibliotheque;

import androidx.annotation.NonNull;

import java.util.Collection;

public class Books {
    public int id;
    public String titre;
    public String auteur;
    public String motCles;
    public String resume;

    public Books(){}

    public Books(int id, String titre, String auteur, String motCles, String resume){
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.motCles = motCles;
        this.resume = resume;
    }

    public String getTitre() {
        return titre;
    }

    public String getAuteur() {
        return this.auteur;
    }

    public String getMotCles() {
        return this.motCles;
    }

    public String getResume() { return this.resume; }

    public int getId(){
        return this.id;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMotCles(String motCles) {
        this.motCles = motCles;
    }

    public void setResume(String resume) { this.resume = resume; }

    public void setTitre(String titre) {
        this.titre = titre;
    }


    @NonNull
    @Override
    public String toString() {
        return this.getId()+this.getTitre()+this.getAuteur()+this.getMotCles()+this.getResume();
    }
}
