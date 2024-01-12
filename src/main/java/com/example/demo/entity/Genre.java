package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Genre {
    @Id
    private String nom;
    private int nbEmprunts = 0;

    @JsonIgnore
    @OneToMany(mappedBy = "genre")
    private List<Document> documents;

    public Genre() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNbEmprunts() {
        return nbEmprunts;
    }

    public void setNbEmprunts(int nbEmprunts) {
        this.nbEmprunts = nbEmprunts;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public void addDocuments(Document document) {
        this.documents.add(document);
    }
}
