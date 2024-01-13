package com.example.demo.controller;


import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


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
            System.err.println(e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/empruntable/{code}")
    public ResponseEntity<Document> makeEmpruntable(@PathVariable String code) {
            Document doc;
            if(audioRepo.findById(code).isPresent()) {
                doc = audioRepo.findById(code).get();
                doc.setEmpruntable(true);
                audioRepo.save((Audio) doc);
            } else if(livreRepo.findById(code).isPresent()) {
                doc = livreRepo.findById(code).get();
                doc.setEmpruntable(true);
                livreRepo.save((Livre) doc);
            } else if(videoRepo.findById(code).isPresent()) {
                doc = videoRepo.findById(code).get();
                doc.setEmpruntable(true);
                videoRepo.save((Video) doc);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(doc,HttpStatus.OK);
    }

    @GetMapping("/get/all")
    public ResponseEntity<HashMap<String, List>> getAll() {
        List<Audio> audios = audioRepo.findAll();
        List<Video> videos = videoRepo.findAll();
        List<Livre> livres = livreRepo.findAll();

        return getHashMapResponseEntity(audios, videos, livres);
    }

    private ResponseEntity<HashMap<String, List>> getHashMapResponseEntity(List<Audio> audios, List<Video> videos, List<Livre> livres) {
        HashMap<String, List> docs = new HashMap<>();
        docs.put("audios", audios);
        docs.put("videos", videos);
        docs.put("livres", livres);


        return new ResponseEntity<>(docs, HttpStatus.OK);
    }

    @GetMapping("/get/genre/{nom}")
    public ResponseEntity<HashMap<String, List>> getMethodName(@PathVariable String nom) {

        try {
            Genre genre = genreRepo.findById(nom).orElseThrow(() -> new RuntimeException("Genre not found"));

            List<Audio> audios = audioRepo.findAllByGenre(genre);
            List<Video> videos = videoRepo.findAllByGenre(genre);
            List<Livre> livres = livreRepo.findAllByGenre(genre);

            return getHashMapResponseEntity(audios, videos, livres);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
