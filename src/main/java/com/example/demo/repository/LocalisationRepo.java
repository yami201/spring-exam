package com.example.demo.repository;

import com.example.demo.entity.Localisation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalisationRepo extends JpaRepository<Localisation,String> {
}
