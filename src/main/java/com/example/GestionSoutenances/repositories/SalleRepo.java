package com.example.GestionSoutenances.repositories;

import com.example.GestionSoutenances.entities.Salle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface SalleRepo extends JpaRepository<Salle, Integer> {
    List<Salle> findSallesDisponibles(LocalDate date, LocalTime debut, LocalTime fin);
}
