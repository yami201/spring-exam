package com.example.demo.repository;

import com.example.demo.entity.Audio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AudioRepo extends JpaRepository<Audio,String> {

}
