package com.example.GestionSoutenances.entities;
import com.example.GestionSoutenances.enums.EnumMention;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Soutenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSoutenance;

    private String sujet;
    private float moyenne;

    @Enumerated(EnumType.STRING)
    private EnumMention mention;

    @OneToOne
    @JsonIgnoreProperties({"soutenance"})
    private Etudiant etudiant;

    @ManyToOne
    @JsonIgnoreProperties({"soutenancesEncadrees"})
    private Enseignant encadrant;

    @ManyToOne
    @JsonIgnoreProperties({"soutenance"})
    private Salle salle;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
private CreneauHoraire creneauHoraire;

    @OneToMany(mappedBy = "soutenance")
     @JsonIgnore 
    private List<Note> notes;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "soutenance")
    private Jury jury;
}