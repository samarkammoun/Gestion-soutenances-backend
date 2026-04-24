package com.example.GestionSoutenances.repositories;

import com.example.GestionSoutenances.entities.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnseignantRepo extends JpaRepository<Enseignant, Integer> {
}
