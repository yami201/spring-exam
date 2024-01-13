package com.example.demo.repository;

import com.example.demo.entity.Audio;
import com.example.demo.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AudioRepo extends JpaRepository<Audio,String> {

    List<Audio> findAllByGenre(Genre genre);

}
