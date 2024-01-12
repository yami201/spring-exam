package com.example.demo.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table
public class Audio extends Document{
    private String classification;
    private final int DUREE = 4*7;
    private final double TARIF = 1.0;

    public Audio() {
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public int getDUREE() {
        return DUREE;
    }

    public double getTARIF() {
        return TARIF;
    }
}
