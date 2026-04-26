package com.example.GestionSoutenances.services;

import com.example.GestionSoutenances.entities.Disponibilite;
import com.example.GestionSoutenances.repositories.DisponibiliteRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DisponibiliteService {

    private final DisponibiliteRepo disponibiliteRepository;

    public Disponibilite ajouterDisponibilite(Disponibilite d) {
        return disponibiliteRepository.save(d);
    }

    public List<Disponibilite> getDisponibilitesByEnseignant(int enseignantId) {
        return disponibiliteRepository.findByEnseignantIdEnseignant(enseignantId);
    }

    public List<Disponibilite> getDisponibilitesByDate(LocalDate date) {
        return disponibiliteRepository.findByDate(date);
    }
}
