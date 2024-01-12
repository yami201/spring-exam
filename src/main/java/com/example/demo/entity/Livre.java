package com.example.demo.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table
public class Livre extends Document{
    private final int DUREE = 5*7;
    private final double TARIF = 0.5;
    private int nombrePage;

    public Livre() {
    }

    public int getDUREE() {
        return DUREE;
    }

    public double getTARIF() {
        return TARIF;
    }

    public int getNombrePage() {
        return nombrePage;
    }

    public void setNombrePage(int nombrePage) {
        this.nombrePage = nombrePage;
    }
}
