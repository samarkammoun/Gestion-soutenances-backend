package com.example.GestionSoutenances.controllers;

import com.example.GestionSoutenances.entities.Etudiant;
import com.example.GestionSoutenances.services.EtudiantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/etudiants")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EtudiantController {

    private final EtudiantService etudiantService;

    @GetMapping
    public List<Etudiant> getAll() {
        return etudiantService.getAllEtudiants();
    }

    @GetMapping("/{id}")
    public Etudiant getById(@PathVariable int id) {
        return etudiantService.getEtudiantById(id);
    }
}