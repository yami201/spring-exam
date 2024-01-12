package com.example.demo.controller;


import com.example.demo.entity.Genre;
import com.example.demo.repository.GenreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genre")
public class GenreController {
    @Autowired
    GenreRepo genreRepo;

    @GetMapping("/get/all")
    public ResponseEntity<List<Genre>> getAll() {
        return new ResponseEntity<>(genreRepo.findAll(),HttpStatus.OK);
    }

    @GetMapping("/get/{nom}")
    public ResponseEntity<Genre> getByName(@PathVariable String nom) {
        try {
            return genreRepo.findById(nom)
                    .map(
                            genre -> new ResponseEntity<>(genre,HttpStatus.OK)
                    ).orElseThrow(
                            () ->
                                    new RuntimeException("Genre not found: "+nom)

                    );
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Genre> createGenre(@RequestBody Genre genre) {
        return new ResponseEntity<>(genreRepo.save(genre), HttpStatus.CREATED);
    }

    @PutMapping("/update/{nom}/{nbEmprunts}")
    public ResponseEntity<Genre> updateGenre(@PathVariable String nom, @PathVariable int nbEmprunts) {
        try {
            return genreRepo.findById(nom)
                    .map(
                            genre -> {
                                genre.setNbEmprunts(nbEmprunts);
                                genreRepo.save(genre);
                                return new ResponseEntity<>(genre,HttpStatus.OK);
                            }
                    ).orElseThrow(
                            () ->
                                 new RuntimeException("Genre not found: "+nom)

                    );
        } catch (RuntimeException e) {

           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{nom}")
    public ResponseEntity<HttpStatus> deleteGenre(@PathVariable String nom) {
        genreRepo.deleteById(nom);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
