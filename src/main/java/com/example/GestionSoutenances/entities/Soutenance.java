package com.example.GestionSoutenances.entities;

import com.example.GestionSoutenances.enums.EnumMention;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Soutenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSoutenance;

    private String sujet;
    private float moyenne;

    @Enumerated(EnumType.STRING)
    private EnumMention mention;

    @OneToOne
    private Etudiant etudiant;

    @ManyToOne
    private Encadrant encadrant;

    @ManyToOne
    private Salle salle;

    @OneToOne
    private CreneauHoraire creneauHoraire;

    @OneToMany(mappedBy = "soutenance")
    private List<Note> notes;

    @OneToOne(mappedBy = "soutenance")
    private Jury jury;
}