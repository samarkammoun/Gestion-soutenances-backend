package com.example.GestionSoutenances.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreneauHoraire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCreneau;

    private LocalDate date;
    private LocalTime heureDebut;
    private LocalTime heureFin;

    @OneToOne(mappedBy = "creneauHoraire")
    private Soutenance soutenance;
}