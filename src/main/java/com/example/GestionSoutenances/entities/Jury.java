package com.example.GestionSoutenances.entities;

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
    private Enseignant rapporteur;

    @ManyToOne
    private Enseignant president;

    @ManyToOne
    private Enseignant examinateur;

    @OneToOne
    private Soutenance soutenance;
}