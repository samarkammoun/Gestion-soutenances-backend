package com.example.GestionSoutenances.repositories;

import com.example.GestionSoutenances.entities.Salle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalleRepo extends JpaRepository<Salle, Integer> {
}
