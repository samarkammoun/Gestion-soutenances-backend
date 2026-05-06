package com.example.GestionSoutenances.controllers;

import com.example.GestionSoutenances.entities.Jury;
import com.example.GestionSoutenances.services.JuryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jury")
@RequiredArgsConstructor
public class JuryController {

    private final JuryService juryService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Jury jury) {
        try {
            return ResponseEntity.ok(juryService.createJury(jury));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        try {
            return ResponseEntity.ok(juryService.getJuryById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}