package com.example.GestionSoutenances.services;

import com.example.GestionSoutenances.entities.Jury;
import com.example.GestionSoutenances.entities.Note;
import com.example.GestionSoutenances.entities.Soutenance;
import com.example.GestionSoutenances.repositories.EnseignantRepo;
import com.example.GestionSoutenances.repositories.NoteRepo;
import com.example.GestionSoutenances.repositories.SoutenanceRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class NoteService {

    private final NoteRepo noteRepo;
    private final SoutenanceRepo soutenanceRepo;
    private final EnseignantRepo enseignantRepo;

    public Note ajouterNote(int soutenanceId, int enseignantId, float valeur) {

        Soutenance soutenance = soutenanceRepo.findById(soutenanceId)
                .orElseThrow(() -> new RuntimeException("Soutenance introuvable"));

        Jury jury = soutenance.getJury();

        // Vérifier si l’enseignant appartient au jury
        boolean estMembreJury =
                jury.getPresident().getIdEnseignant()==(enseignantId) ||
                        jury.getRapporteur().getIdEnseignant()==(enseignantId) ||
                        jury.getExaminateur().getIdEnseignant()==(enseignantId);

        if (!estMembreJury) {
            throw new RuntimeException("Seuls les membres du jury peuvent attribuer une note");
        }

        // Création de la note
        Note note = new Note();
        note.setValeur(valeur);
        note.setEnseignant(
                enseignantRepo.findById(enseignantId).orElseThrow()
        );
        note.setSoutenance(soutenance);

        return noteRepo.save(note);
    }
}
