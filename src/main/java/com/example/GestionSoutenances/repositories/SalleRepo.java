package com.example.GestionSoutenances.repositories;

import com.example.GestionSoutenances.entities.Salle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface SalleRepo extends JpaRepository<Salle, Integer> {
    
    @Query("SELECT s FROM Salle s WHERE NOT EXISTS (" +
            "SELECT 1 FROM Soutenance so " +
            "JOIN so.creneauHoraire ch " +
            "WHERE so.salle = s " +
            "AND ch.date = :date " +
            "AND ch.heureDebut < :heureFin " +
            "AND ch.heureFin > :heureDebut)")
    List<Salle> findSallesDisponibles(
            @Param("date") LocalDate date,
            @Param("heureDebut") LocalTime heureDebut,
            @Param("heureFin") LocalTime heureFin);
}
