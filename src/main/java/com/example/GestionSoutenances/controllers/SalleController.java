package com.example.GestionSoutenances.controllers;

import com.example.GestionSoutenances.entities.Salle;
import com.example.GestionSoutenances.services.SalleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/salles")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SalleController {

    private final SalleService salleService;

    @GetMapping
    public List<Salle> getAll() {
        return salleService.getAllSalles();
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<Salle>> getDisponibles(
            @RequestParam LocalDate date,
            @RequestParam LocalTime heureDebut,
            @RequestParam LocalTime heureFin) {
        return ResponseEntity.ok(salleService.findSallesDisponibles(date, heureDebut, heureFin));
    }
}