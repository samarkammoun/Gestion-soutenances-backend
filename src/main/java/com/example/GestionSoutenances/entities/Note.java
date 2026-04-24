package com.example.GestionSoutenances.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNote;

    private float valeur;
    private String commentaire;

    @ManyToOne
    private Enseignant enseignant;

    @ManyToOne
    private Soutenance soutenance;
}
