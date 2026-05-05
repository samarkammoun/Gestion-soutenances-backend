package com.example.GestionSoutenances.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


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
     @JsonIgnore 
    private List<Soutenance> soutenancesEncadrees;

    @OneToMany(mappedBy = "enseignant", cascade = CascadeType.ALL, orphanRemoval = true)
     @JsonIgnore 
    private List<Disponibilite> disponibilites;

}