package com.example.demo.controller;


import com.example.demo.entity.Client;
import com.example.demo.repository.AudioRepo;
import com.example.demo.repository.ClientRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    ClientRepo clientRepo;
    
    AudioRepo audioRepo;
    @PostMapping("/create")
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        try {
            return new ResponseEntity<>(clientRepo.save(client), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Client>> getAllClients() {
        return new ResponseEntity<>(clientRepo.findAll(), HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<Client> getMethodName(
        @RequestParam String nom,
        @RequestParam String prenom
    ) {
        return new ResponseEntity<>(clientRepo.findByNomAndPrenom(nom, prenom), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable long id) {
        try {
            clientRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
