package com.example.GestionSoutenances.repositories;

import com.example.GestionSoutenances.entities.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtudiantRepo extends JpaRepository<Etudiant, Integer> {
}
