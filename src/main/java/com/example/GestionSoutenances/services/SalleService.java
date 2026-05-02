package com.example.GestionSoutenances.services;

import com.example.GestionSoutenances.entities.Salle;
import com.example.GestionSoutenances.repositories.SalleRepo;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SalleService {

    private final SalleRepo salleRepository;
    public Salle createSalle(Salle s) {
        return salleRepository.save(s);
    }

    public List<Salle> getAllSalles() {
        return salleRepository.findAll();
    }
    

    public List<Salle> findSallesDisponibles(LocalDate date, LocalTime heureDebut, LocalTime heureFin) {
        return salleRepository.findSallesDisponibles(date, heureDebut, heureFin);
    }

    public Salle updateSalle (Salle salle, int id){
        Salle salleToUpdate = salleRepository.findById(id)
            .orElseThrow (() -> new EntityNotFoundException("Salle inexistante !"));
        if(salleToUpdate != null){
            salleToUpdate.setCapacite(salle.getCapacite());
            salleToUpdate.setNomSalle(salle.getNomSalle());
            return salleRepository.save(salleToUpdate);
        }
        return null;
    }


    public void supprimerSalle (int id){
        Salle salle = salleRepository.findById(id)
            .orElseThrow (() -> new EntityNotFoundException("Salle inexistante !"));
        if (salle!=null){
            salleRepository.delete(salle);
        }
    }


}
