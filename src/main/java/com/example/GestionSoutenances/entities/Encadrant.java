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
public class Encadrant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEncadrant;

    private String nom;
    private String prenom;
    private String email;

    @OneToMany(mappedBy = "encadrant")
    private List<Soutenance> soutenances;
}