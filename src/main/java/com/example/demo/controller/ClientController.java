package com.example.demo.controller;


import com.example.demo.entity.Document;
import com.example.demo.entity.FicheEmprunt;
import com.example.demo.entity.Livre;
import com.example.demo.repository.AudioRepo;
import com.example.demo.repository.LivreRepo;
import com.example.demo.repository.VideoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    AudioRepo audioRepo;
    @Autowired
    LivreRepo livreRepo;
    @Autowired
    VideoRepo videoRepo;

//    @PostMapping("/emprunter/{idClient}/{codeDoc}")
//    public ResponseEntity<FicheEmprunt> emprunterDoc(
//            @PathVariable long idClient,
//            @PathVariable String codeDoc
//    ) {
//        FicheEmprunt fiche = new FicheEmprunt();
//        fiche.setDateEmprunt(new Date());
//        long time = new Date().getTime();
//        time += 2 * 24 * 3600 * 1000;
//        fiche.setDateRappel(new Date(time));
//        time += 2 * 24 * 3600 * 1000;
//        fiche.setDateLimite(new Date(time));
//
//        Document doc;
//
//        audioRepo.finB
//    }
}
