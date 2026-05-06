package com.example.GestionSoutenances.controllers;

import com.example.GestionSoutenances.entities.Disponibilite;
import com.example.GestionSoutenances.entities.Enseignant;
import com.example.GestionSoutenances.services.EnseignantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enseignants")
@RequiredArgsConstructor
public class EnseignantController {

    private final EnseignantService enseignantService;

    @GetMapping
    public List<Enseignant> getAll() {
        return enseignantService.getAllEnseignants();
    }

    @GetMapping("/{id}")
    public Enseignant getById(@PathVariable int id) {
        return enseignantService.getEnseignantById(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Enseignant enseignant) {
        try {
            return ResponseEntity.ok(enseignantService.createEnseignant(enseignant));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{id}/disponibilites")
    public ResponseEntity<?> addDisponibilite(@PathVariable int id, @RequestBody Disponibilite disponibilite) {
        try {
            return ResponseEntity.ok(enseignantService.ajouterDisponibilite(id, disponibilite));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}