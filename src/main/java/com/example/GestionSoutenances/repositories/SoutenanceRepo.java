package com.example.GestionSoutenances.repositories;

import com.example.GestionSoutenances.entities.Etudiant;
import com.example.GestionSoutenances.entities.Soutenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoutenanceRepo extends JpaRepository<Soutenance, Integer> {
    Soutenance findByEtudiant(Etudiant etudiant);

}
