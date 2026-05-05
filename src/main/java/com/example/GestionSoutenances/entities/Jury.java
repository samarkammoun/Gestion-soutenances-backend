package com.example.GestionSoutenances.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Jury {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idJury;

    @ManyToOne
    @JsonIgnoreProperties({"soutenancesEncadrees", "disponibilites", "notes"})
    private Enseignant rapporteur;

    @ManyToOne
    @JsonIgnoreProperties({"soutenancesEncadrees", "disponibilites", "notes"})
    private Enseignant president;

    @ManyToOne
    @JsonIgnoreProperties({"soutenancesEncadrees", "disponibilites", "notes"})
    private Enseignant examinateur;

    @OneToOne
    @JsonIgnore
    private Soutenance soutenance;
}