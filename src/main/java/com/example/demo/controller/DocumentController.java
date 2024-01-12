package com.example.demo.controller;


import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/document")
public class DocumentController {
    @Autowired
    AudioRepo audioRepo;
    @Autowired
    LivreRepo livreRepo;
    @Autowired
    VideoRepo videoRepo;
    @Autowired
    GenreRepo genreRepo;
    @Autowired
    LocalisationRepo localisationRepo;

    @PostMapping("/create/audio")
    public ResponseEntity<Audio> createAudio(@RequestBody Audio audio) {
        try {
            Audio doc = audioRepo.save(audio);
            Genre genre = doc.getGenre();
            Localisation localisation = doc.getLocalisation();

            if(genre != null) {
                genre.addDocuments(doc);
                genreRepo.save(genre);
            }
            if(localisation != null) {
                localisation.addDocuments(doc);
                localisationRepo.save(localisation);
            }
            return new ResponseEntity<>(doc,HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
