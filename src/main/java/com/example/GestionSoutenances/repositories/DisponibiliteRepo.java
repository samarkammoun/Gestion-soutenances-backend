package com.example.GestionSoutenances.repositories;

import com.example.GestionSoutenances.entities.Disponibilite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DisponibiliteRepo extends JpaRepository<Disponibilite, Integer> {

    List<Disponibilite> findByEnseignantIdEnseignant(int enseignantId);
    List<Disponibilite> findByDate(LocalDate date);

    List<Disponibilite> findByEnseignantIdEnseignantAndDate(int enseignantId, LocalDate date);


}
