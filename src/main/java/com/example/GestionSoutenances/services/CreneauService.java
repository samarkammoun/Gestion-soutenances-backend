package com.example.GestionSoutenances.services;

import com.example.GestionSoutenances.dto.CreneauResult;
import com.example.GestionSoutenances.entities.Disponibilite;
import com.example.GestionSoutenances.repositories.DisponibiliteRepo;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreneauService {

    private final DisponibiliteRepo disponibiliteRepository;
    private static final LocalTime HEURE_DEBUT_TRAVAIL = LocalTime.of(8, 0);
    private static final LocalTime HEURE_FIN_TRAVAIL = LocalTime.of(17, 0);

    private List<Disponibilite> intersect(
            List<Disponibilite> list1,
            List<Disponibilite> list2
    ) {

        List<Disponibilite> result = new ArrayList<>();

        for (Disponibilite d1 : list1) {
            for (Disponibilite d2 : list2) {

                if (d1.getHeureFin().isAfter(d2.getHeureDebut())
                        && d2.getHeureFin().isAfter(d1.getHeureDebut())) {

                    LocalTime start = d1.getHeureDebut().isAfter(d2.getHeureDebut())
                            ? d1.getHeureDebut()
                            : d2.getHeureDebut();

                    LocalTime end = d1.getHeureFin().isBefore(d2.getHeureFin())
                            ? d1.getHeureFin()
                            : d2.getHeureFin();

                    if (start.isBefore(end)) {
                        Disponibilite d = new Disponibilite();
                        d.setDate(d1.getDate());
                        d.setHeureDebut(start);
                        d.setHeureFin(end);

                        result.add(d);
                    }
                }
            }
        }

        return result;
    }


    // Permet de trouver un creneau commun entre le jury et encadrant pour une date et une durée déterminée (approximative) de la soutenance
    public List<CreneauResult> trouverCreneauxSoutenance(
            int encadrantId,
            int presidentId,
            int rapporteurId,
            int examinateurId,
            LocalDate date,
            int dureeMinutes
    ) {

        List<Integer> ids = List.of(encadrantId, presidentId, rapporteurId, examinateurId);

        // 1. Récupérer toutes les disponibilités
        List<List<Disponibilite>> toutesDispos = new ArrayList<>();

        for (int id : ids) {
            List<Disponibilite> dispos =
                    disponibiliteRepository.findByEnseignantIdEnseignantAndDate(id, date);

            if (dispos.isEmpty()) {
                return List.of(); // une personne n’est pas dispo
            }

            toutesDispos.add(dispos);
        }

        // 2. Intersection progressive
        List<Disponibilite> intersection = toutesDispos.get(0);

        for (int i = 1; i < toutesDispos.size(); i++) {
            intersection = intersect(intersection, toutesDispos.get(i));
        }

        // 3. Transformer en créneaux exploitables
        List<CreneauResult> result = new ArrayList<>();

        for (Disponibilite d : intersection) {

            // Ajustement aux heures de travail
            LocalTime start = d.getHeureDebut().isBefore(HEURE_DEBUT_TRAVAIL)
                    ? HEURE_DEBUT_TRAVAIL
                    : d.getHeureDebut();

            LocalTime end = d.getHeureFin().isAfter(HEURE_FIN_TRAVAIL)
                    ? HEURE_FIN_TRAVAIL
                    : d.getHeureFin();

            // Si hors plage → ignorer
            if (!start.isBefore(end)) continue;

            // Génération des créneaux
            while (!start.plusMinutes(dureeMinutes).isAfter(end)) {

                result.add(new CreneauResult(
                        date,
                        start,
                        start.plusMinutes(dureeMinutes)
                ));

                start = start.plusMinutes(15);
            }
        }

        return result;
    }

}
