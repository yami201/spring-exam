package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table
public class Video extends Document {
    private final int DUREE = 2*7;
    private final double TARIF = 1.5;

    private int dureeFilm;
    private String mentionLegale;

    public Video() {
    }

    public int getDUREE() {
        return DUREE;
    }

    public double getTARIF() {
        return TARIF;
    }

    public int getDureeFilm() {
        return dureeFilm;
    }

    public void setDureeFilm(int dureeFilm) {
        this.dureeFilm = dureeFilm;
    }

    public String getMentionLegale() {
        return mentionLegale;
    }

    public void setMentionLegale(String mentionLegale) {
        this.mentionLegale = mentionLegale;
    }
}
