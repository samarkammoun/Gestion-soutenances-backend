package com.example.GestionSoutenances.services;

import com.example.GestionSoutenances.dto.SoutenanceRequest;
import org.springframework.stereotype.Service;

import com.example.GestionSoutenances.dto.CreneauResult;
import com.example.GestionSoutenances.entities.*;
import com.example.GestionSoutenances.repositories.*;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SoutenanceService {

    private final SoutenanceRepo soutenanceRepository;
    private final EtudiantRepo etudiantRepository;
    private final EnseignantRepo enseignantRepository;
    private final SalleRepo salleRepository;
    private final CreneauService creneauService;
    private final CreneauHoraireRepo creneauHoraireRepo;

    public Soutenance createSoutenance(SoutenanceRequest request) {

        Etudiant etudiant = etudiantRepository.findById(request.getEtudiantId())
                .orElseThrow(() -> new RuntimeException("Etudiant introuvable"));

        Enseignant encadrant = enseignantRepository.findById(request.getEncadrantId())
                .orElseThrow(() -> new RuntimeException("Encadrant introuvable"));

        Enseignant president = enseignantRepository.findById(request.getPresidentId()).orElseThrow();
        Enseignant rapporteur = enseignantRepository.findById(request.getRapporteurId()).orElseThrow();
        Enseignant examinateur = enseignantRepository.findById(request.getExaminateurId()).orElseThrow();

        List<CreneauResult> creneaux =
                creneauService.trouverCreneauxSoutenance(
                        request.getEncadrantId(),
                        request.getPresidentId(),
                        request.getRapporteurId(),
                        request.getExaminateurId(),
                        request.getDate(),
                        request.getDureeMinutes()
                );

        if (creneaux.isEmpty()) {
            throw new RuntimeException("Aucun créneau commun");
        }

        CreneauResult creneauChoisi = creneaux.get(0);

        List<Salle> sallesDisponibles =
                salleRepository.findSallesDisponibles(
                        creneauChoisi.getDate(),
                        creneauChoisi.getHeureDebut(),
                        creneauChoisi.getHeureFin()
                );

        if (sallesDisponibles.isEmpty()) {
            throw new RuntimeException("Aucune salle disponible");
        }

        Salle salleChoisie = sallesDisponibles.get(0);

        // Jury
        Jury jury = new Jury();
        jury.setPresident(president);
        jury.setRapporteur(rapporteur);
        jury.setExaminateur(examinateur);

        // Soutenance
        Soutenance s = new Soutenance();
        s.setEtudiant(etudiant);
        s.setEncadrant(encadrant);
        s.setSalle(salleChoisie);
        s.setJury(jury);

        // 👇 AJOUT IMPORTANT
        s.setSujet(request.getSujet());

        // Créneau
        CreneauHoraire creneauHoraire = new CreneauHoraire();
        creneauHoraire.setDate(creneauChoisi.getDate());
        creneauHoraire.setHeureDebut(creneauChoisi.getHeureDebut());
        creneauHoraire.setHeureFin(creneauChoisi.getHeureFin());
        creneauHoraire.setSoutenance(s);

        creneauHoraireRepo.save(creneauHoraire);
        s.setCreneauHoraire(creneauHoraire);

        return soutenanceRepository.save(s);
    }
}