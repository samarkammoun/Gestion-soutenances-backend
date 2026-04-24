package com.example.GestionSoutenances.repositories;

import com.example.GestionSoutenances.entities.Jury;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JuryRepo extends JpaRepository<Jury, Integer> {
}
