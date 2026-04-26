package com.example.GestionSoutenances.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Enseignant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEnseignant;

    private String nom;
    private String prenom;
    private String grade;

    /**
     * Soutenances où cet enseignant est encadrant
     */
    @OneToMany(mappedBy = "encadrant")
    private List<Soutenance> soutenancesEncadrees;

    @OneToMany(mappedBy = "enseignant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Disponibilite> disponibilites;

}