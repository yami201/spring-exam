package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jdk.jfr.Category;

import java.util.Date;
import java.util.List;

@Entity
@Table
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nom;
    private String prenom;
    private Date dateInscripton;
    private Date dateRenouvellement;
    private int nbEmpruntsEffectues = 0;
    private int nbEmpruntsDepases = 0;
    private int nbEmpruntsEncours = 0;
    private int codeReduction;
    @ManyToOne
    @JoinColumn(name = "categorie")
    private CategorieClient categorie;

    public Client() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Date getDateInscripton() {
        return dateInscripton;
    }

    public void setDateInscripton(Date dateInscripton) {
        this.dateInscripton = dateInscripton;
    }

    public Date getDateRenouvellement() {
        return dateRenouvellement;
    }

    public void setDateRenouvellement(Date dateRenouvellement) {
        this.dateRenouvellement = dateRenouvellement;
    }

    public int getNbEmpruntsEffectues() {
        return nbEmpruntsEffectues;
    }

    public void setNbEmpruntsEffectues(int nbEmpruntsEffectues) {
        this.nbEmpruntsEffectues = nbEmpruntsEffectues;
    }

    public int getNbEmpruntsDepases() {
        return nbEmpruntsDepases;
    }

    public void setNbEmpruntsDepases(int nbEmpruntsDepases) {
        this.nbEmpruntsDepases = nbEmpruntsDepases;
    }

    public int getNbEmpruntsEncours() {
        return nbEmpruntsEncours;
    }

    public void setNbEmpruntsEncours(int nbEmpruntsEncours) {
        this.nbEmpruntsEncours = nbEmpruntsEncours;
    }

    public int getCodeReduction() {
        return codeReduction;
    }

    public void setCodeReduction(int codeReduction) {
        this.codeReduction = codeReduction;
    }

    public CategorieClient getCategorie() {
        return categorie;
    }

    public void setCategorie(CategorieClient categorie) {
        this.categorie = categorie;
    }
}
