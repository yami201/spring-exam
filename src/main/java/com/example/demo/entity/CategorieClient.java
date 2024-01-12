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

}
