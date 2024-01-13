package com.example.demo.repository;

import com.example.demo.entity.Audio;
import com.example.demo.entity.Genre;
import com.example.demo.entity.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


public interface LivreRepo extends JpaRepository<Livre,String> {

    List<Livre> findAllByGenre(Genre genre);
}
