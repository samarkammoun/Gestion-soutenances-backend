package com.example.GestionSoutenances.controllers;

import com.example.GestionSoutenances.dto.ResultatDto;
import com.example.GestionSoutenances.dto.SoutenanceRequest;
import com.example.GestionSoutenances.entities.Soutenance;
import com.example.GestionSoutenances.services.SoutenanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/soutenances")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SoutenanceController {

    private final SoutenanceService soutenanceService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody SoutenanceRequest request) {
        try {
            Soutenance s = soutenanceService.createSoutenance(request);
            return ResponseEntity.ok(s);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<Soutenance> getAll() {
        return soutenanceService.getAllSoutenances();
    }

    // Attention: getSoutenanceById() n'existe PAS dans SoutenanceService
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        return ResponseEntity.status(501).body(
                "Méthode non implémentée. Ajoutez getSoutenanceById() dans SoutenanceService"
        );
    }

    @GetMapping("/etudiant/{etudiantId}")
    public ResponseEntity<Soutenance> getByEtudiant(@PathVariable int etudiantId) {
        Soutenance soutenance = soutenanceService.getSoutenanceByEtudiant(etudiantId);
        if (soutenance != null) {
            return ResponseEntity.ok(soutenance);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        soutenanceService.annulerSoutenance(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/calculer-resultat")
    public ResponseEntity<?> calculer(@PathVariable int id) {
        try {
            soutenanceService.calculerResultat(id);
            return ResponseEntity.ok().body("Résultat calculé avec succès");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}/resultat")
    public ResponseEntity<ResultatDto> getResultat(@PathVariable int id) {
        return ResponseEntity.ok(soutenanceService.consulterResultat(id));
    }
}