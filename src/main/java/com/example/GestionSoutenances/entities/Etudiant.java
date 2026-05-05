package com.example.GestionSoutenances.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Etudiant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEtudiant;

    private String nom;
    private String prenom;
    private String email;

    @OneToOne(mappedBy = "etudiant", orphanRemoval = true)
    
    private Soutenance soutenance;
}