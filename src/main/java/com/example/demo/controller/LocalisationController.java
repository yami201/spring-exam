package com.example.demo.controller;


import com.example.demo.entity.Localisation;
import com.example.demo.repository.LocalisationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/localisation")
public class LocalisationController {
    @Autowired
    LocalisationRepo localisationRepo;

    @PostMapping("/create")
    public ResponseEntity<Localisation> createLocalisation(@RequestBody Localisation localisation) {
        return new ResponseEntity<>(localisationRepo.save(localisation), HttpStatus.CREATED);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Localisation>> getAll() {
        return new ResponseEntity<>(localisationRepo.findAll(),HttpStatus.OK);
    }

}
