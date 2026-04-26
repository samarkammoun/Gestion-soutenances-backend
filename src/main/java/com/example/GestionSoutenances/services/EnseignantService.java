package com.example.GestionSoutenances.services;

import com.example.GestionSoutenances.entities.Disponibilite;
import com.example.GestionSoutenances.entities.Enseignant;
import com.example.GestionSoutenances.repositories.DisponibiliteRepo;
import com.example.GestionSoutenances.repositories.EnseignantRepo;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnseignantService {

    private final EnseignantRepo enseignantRepository;
    private final DisponibiliteRepo disponibiliteRepository;

    public Enseignant createEnseignant(Enseignant e) {
        return enseignantRepository.save(e);
    }

    public List<Enseignant> getAllEnseignants() {
        return enseignantRepository.findAll();
    }

    public Enseignant getEnseignantById(int id) {
        return enseignantRepository.findById(Math.toIntExact(id)).orElse(null);
    }

    public Disponibilite ajouterDisponibilite(int enseignantId, Disponibilite d) {
        Enseignant e = getEnseignantById(enseignantId);
        d.setEnseignant(e);
        return disponibiliteRepository.save(d);
    }
}
