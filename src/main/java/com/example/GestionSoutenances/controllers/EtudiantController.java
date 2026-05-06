package com.example.GestionSoutenances.controllers;

import com.example.GestionSoutenances.entities.Etudiant;
import com.example.GestionSoutenances.services.EtudiantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/etudiants")
@RequiredArgsConstructor
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

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Etudiant etudiant) {
        try {
            return ResponseEntity.ok(etudiantService.createEtudiant(etudiant));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Etudiant etudiant) {
        try {
            return ResponseEntity.ok(etudiantService.updateEtudiant(id, etudiant));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        try {
            etudiantService.deleteEtudiant(id);
            return ResponseEntity.ok("Etudiant supprimé avec succès");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}