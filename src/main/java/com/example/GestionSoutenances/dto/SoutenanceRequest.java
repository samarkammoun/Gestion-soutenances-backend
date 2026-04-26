package com.example.GestionSoutenances.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class SoutenanceRequest {

    private int etudiantId;
    private int encadrantId;
    private int presidentId;
    private int rapporteurId;
    private int examinateurId;

    private LocalDate date;
    private int dureeMinutes;

    private String sujet;
}