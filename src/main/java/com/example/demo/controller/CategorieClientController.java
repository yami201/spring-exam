package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.CategorieClient;
import com.example.demo.repository.CategorieClientRepo;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/categorieClient")
public class CategorieClientController {
    @Autowired
    CategorieClientRepo categorieClientRepo;

    @PostMapping("/create")
    public ResponseEntity<CategorieClient> createCategorieClient(@RequestBody CategorieClient categorieClient) {
        return new ResponseEntity<>(categorieClientRepo.save(categorieClient), HttpStatus.CREATED);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<CategorieClient>> getMethodName() {
        return new ResponseEntity<>(categorieClientRepo.findAll(), HttpStatus.OK);
    }
    
    
    
}
