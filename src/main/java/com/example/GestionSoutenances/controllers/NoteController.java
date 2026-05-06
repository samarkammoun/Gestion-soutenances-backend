package com.example.GestionSoutenances.controllers;

import com.example.GestionSoutenances.entities.Note;
import com.example.GestionSoutenances.services.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    // Utilise la méthode EXISTANTE : ajouterNote(soutenanceId, enseignantId, valeur)
    @PostMapping
    public ResponseEntity<?> addNote(@RequestBody Map<String, Object> payload) {
        try {
            int soutenanceId = (int) payload.get("soutenanceId");
            int enseignantId = (int) payload.get("enseignantId");
            float valeur = ((Number) payload.get("valeur")).floatValue();

            Note savedNote = noteService.ajouterNote(soutenanceId, enseignantId, valeur);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Note ajoutée avec succès");
            response.put("note", savedNote);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/soutenance/{soutenanceId}")
    public ResponseEntity<?> getNotesBySoutenance(@PathVariable int soutenanceId) {
        try {
            return ResponseEntity.ok(noteService.getNotesBySoutenanceId(soutenanceId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/soutenance/{soutenanceId}/enseignant/{enseignantId}")
    public ResponseEntity<?> getNoteByJury(
            @PathVariable int soutenanceId,
            @PathVariable int enseignantId) {
        try {
            return ResponseEntity.ok(noteService.getNoteBySoutenanceAndEnseignant(soutenanceId, enseignantId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}