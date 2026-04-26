package com.example.GestionSoutenances.services;

import com.example.GestionSoutenances.entities.Etudiant;
import com.example.GestionSoutenances.repositories.EtudiantRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EtudiantService {
    private final EtudiantRepo etudiantRepo;

    public Etudiant createEtudiant(Etudiant etudiant){
        return etudiantRepo.save(etudiant);
    }

    public Etudiant updateEtudiant (int id, Etudiant etudiant){
        Etudiant etudiantToUpdate = etudiantRepo.findById(id)
                .orElseThrow( () -> new EntityNotFoundException("Etudiant inexistant !"));
        if (etudiantToUpdate!=null){
            etudiantToUpdate.setEmail(etudiant.getEmail());
            etudiantToUpdate.setNom(etudiant.getNom());
            etudiantToUpdate.setPrenom(etudiant.getPrenom());
            return etudiantRepo.save(etudiantToUpdate);
        }
        return null;
    }

    public void deleteEtudiant(int id) {
        etudiantRepo.deleteById(id);
    }

    public Etudiant getEtudiantById(int id) {
        return etudiantRepo.findById(id)
                .orElseThrow( () -> new EntityNotFoundException("Etudiant inexistant !"));
    }

    public List<Etudiant> getAllEtudiants() {
        return etudiantRepo.findAll();
    }


}
