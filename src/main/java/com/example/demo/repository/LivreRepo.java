package com.example.demo.repository;

import com.example.demo.entity.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;



public interface LivreRepo extends JpaRepository<Livre,String> {

}
