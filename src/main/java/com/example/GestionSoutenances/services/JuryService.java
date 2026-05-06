package com.example.GestionSoutenances.services;

import com.example.GestionSoutenances.entities.Jury;
import com.example.GestionSoutenances.repositories.JuryRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JuryService {

    private final JuryRepo juryRepository;

    public Jury createJury(Jury jury) {
        return juryRepository.save(jury);
    }
    public Jury getJuryById(int id) {
        return juryRepository.findById(id)
                .orElseThrow(() -> new jakarta.persistence.EntityNotFoundException("Jury introuvable"));
    }
}
