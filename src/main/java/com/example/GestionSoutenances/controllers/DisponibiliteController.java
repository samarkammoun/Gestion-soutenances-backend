package com.example.GestionSoutenances.controllers;

import com.example.GestionSoutenances.entities.Disponibilite;
import com.example.GestionSoutenances.services.DisponibiliteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/disponibilites")
@RequiredArgsConstructor
public class DisponibiliteController {

    private final DisponibiliteService disponibiliteService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Disponibilite disponibilite) {
        try {
            return ResponseEntity.ok(disponibiliteService.ajouterDisponibilite(disponibilite));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/enseignant/{enseignantId}")
    public ResponseEntity<?> getByEnseignant(@PathVariable int enseignantId) {
        try {
            return ResponseEntity.ok(disponibiliteService.getDisponibilitesByEnseignant(enseignantId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<?> getByDate(@PathVariable String date) {
        try {
            return ResponseEntity.ok(disponibiliteService.getDisponibilitesByDate(LocalDate.parse(date)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}