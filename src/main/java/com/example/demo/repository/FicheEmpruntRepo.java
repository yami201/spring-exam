package com.example.demo.repository;

import com.example.demo.entity.FicheEmprunt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FicheEmpruntRepo extends JpaRepository<FicheEmprunt,Long> {
}
