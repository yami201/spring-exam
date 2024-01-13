package com.example.demo.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.entity.Audio;
import com.example.demo.entity.CategorieClient;
import com.example.demo.entity.Client;
import com.example.demo.entity.Document;
import com.example.demo.entity.FicheEmprunt;
import com.example.demo.entity.Genre;
import com.example.demo.entity.Livre;
import com.example.demo.entity.Video;
import com.example.demo.repository.AudioRepo;
import com.example.demo.repository.ClientRepo;
import com.example.demo.repository.FicheEmpruntRepo;
import com.example.demo.repository.GenreRepo;
import com.example.demo.repository.LivreRepo;
import com.example.demo.repository.VideoRepo;


@Controller
@RequestMapping("/ficheEmprunt")
public class FicheEmpruntController {
    @Autowired
    AudioRepo audioRepo;
    @Autowired
    LivreRepo livreRepo;
    @Autowired
    VideoRepo videoRepo;
    @Autowired
    ClientRepo clientRepo;
    @Autowired
    GenreRepo genreRepo;
    @Autowired
    FicheEmpruntRepo ficheEmpruntRepo;

    public double calculerTarif(
        Document document, 
        int codeReduction, 
        boolean actif,
        double coefTarif
        ) {
        double tarif = 0;
        if (document instanceof Audio) {
            tarif = ((Audio) document).getTARIF() * coefTarif;
        } else if (document instanceof Livre) {
            tarif = ((Livre) document).getTARIF() * coefTarif;
        } else if (document instanceof Video) {
            tarif = ((Video) document).getTARIF() * coefTarif;
        }
        if (actif) {
            return tarif * (1 - (double) codeReduction / 100);
        } else {
            return tarif;
        }
    }

    @PostMapping("/emprunter/{idClient}/{codeDoc}")
    public ResponseEntity<FicheEmprunt> emprunterDoc(
            @PathVariable long idClient,
            @PathVariable String codeDoc) {
        FicheEmprunt fiche = new FicheEmprunt();
        Document doc;
        Client client = clientRepo
                .findById(idClient)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        CategorieClient categorieClient = client.getCategorie();


        if (audioRepo.findById(codeDoc).isPresent()) {
            doc = audioRepo.findById(codeDoc).get();
        } else if (livreRepo.findById(codeDoc).isPresent()) {
            doc = livreRepo.findById(codeDoc).get();
        } else if (videoRepo.findById(codeDoc).isPresent()) {
            doc = videoRepo.findById(codeDoc).get();
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(!doc.isEmpruntable()){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        Genre genre = doc.getGenre();
        genre.setNbEmprunts(genre.getNbEmprunts() + 1);
        genreRepo.save(genre);   

        doc.setNbEmprunt(doc.getNbEmprunt() + 1);
        doc.setEmprunte(true);
        
        if (doc instanceof Audio) {
            audioRepo.save((Audio) doc);
        } else if (doc instanceof Livre) {
            livreRepo.save((Livre) doc);
        } else {
            videoRepo.save((Video) doc);
        }

        fiche.setDateEmprunt(new Date());
        long time = new Date().getTime();
        time += 2 * 24 * 3600 * 1000;
        fiche.setDateRappel(new Date(time));
        time += 2 * 24 * 3600 * 1000;
        fiche.setDateLimite(new Date(time));
        fiche.setDepasse(false);
        fiche.setTarif(
            calculerTarif(
                doc, 
                client.getCodeReduction(), 
                categorieClient.isCodeReductionActif(),
                categorieClient.getCoefTarif()
            )
        );

        fiche.setDocument(doc);
        fiche.setClient(client);
        return new ResponseEntity<>(ficheEmpruntRepo.save(fiche), HttpStatus.CREATED);
    }


}
