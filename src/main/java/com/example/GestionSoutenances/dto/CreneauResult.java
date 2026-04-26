package com.example.GestionSoutenances.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor

// Classe utilisée pour trouver un créneau calculé à partir de plusieurs enseignants
// résultat calculé depuis CreneauService.trouverCreneauCommun
public class CreneauResult {

    private LocalDate date;
    private LocalTime heureDebut;
    private LocalTime heureFin;
}