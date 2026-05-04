package com.example.GestionSoutenances.controllers;

import com.example.GestionSoutenances.entities.Enseignant;
import com.example.GestionSoutenances.services.EnseignantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enseignants")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
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
}