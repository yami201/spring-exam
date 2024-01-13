package com.example.demo.repository;

import com.example.demo.entity.Audio;
import com.example.demo.entity.Genre;
import com.example.demo.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoRepo extends JpaRepository<Video,String> {
    List<Video> findAllByGenre(Genre genre);
}
