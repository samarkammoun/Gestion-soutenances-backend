package com.example.GestionSoutenances.repositories;

import com.example.GestionSoutenances.entities.CreneauHoraire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreneauHoraireRepo extends JpaRepository<CreneauHoraire, Integer> {
}
