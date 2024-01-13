package com.example.demo.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class CategorieClient {
    @Id
    private String nomCat;
    private int nbEmpruntsMax;
    private double colisation;
    private double coefTarif;
    private double coefDuree;
    private boolean codeReductionActif;


    @JsonIgnore
    @OneToMany(mappedBy = "categorie")
    private List<Client> clients;

    public CategorieClient() {
    }


    public String getNomCat() {
        return this.nomCat;
    }

    public void setNomCat(String nomCat) {
        this.nomCat = nomCat;
    }

    public int getNbEmpruntsMax() {
        return this.nbEmpruntsMax;
    }

    public void setNbEmpruntsMax(int nbEmpruntsMax) {
        this.nbEmpruntsMax = nbEmpruntsMax;
    }

    public double getColisation() {
        return this.colisation;
    }

    public void setColisation(double colisation) {
        this.colisation = colisation;
    }

    public double getCoefTarif() {
        return this.coefTarif;
    }

    public void setCoefTarif(double coefTarif) {
        this.coefTarif = coefTarif;
    }

    public double getCoefDuree() {
        return this.coefDuree;
    }

    public void setCoefDuree(double coefDuree) {
        this.coefDuree = coefDuree;
    }

    public boolean isCodeReductionActif() {
        return this.codeReductionActif;
    }

    public boolean getCodeReductionActif() {
        return this.codeReductionActif;
    }

    public void setCodeReductionActif(boolean codeReductionActif) {
        this.codeReductionActif = codeReductionActif;
    }

    public List<Client> getClients() {
        return this.clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

}
