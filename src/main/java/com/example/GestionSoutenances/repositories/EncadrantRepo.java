package com.example.GestionSoutenances.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncadrantRepo extends JpaRepository<EncadrantRepo, Integer> {
}
