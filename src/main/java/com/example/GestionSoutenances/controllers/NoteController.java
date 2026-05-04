package com.example.GestionSoutenances.controllers;

import com.example.GestionSoutenances.entities.Note;
import com.example.GestionSoutenances.services.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
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

    // Endpoint GET - retourne une erreur claire car la méthode n'existe PAS
    @GetMapping("/soutenance/{soutenanceId}")
    public ResponseEntity<?> getNotesBySoutenance(@PathVariable int soutenanceId) {
        return ResponseEntity.status(501).body(
                "Cette fonctionnalité n'est pas encore implémentée. " +
                        "Méthode manquante dans NoteService: getNotesBySoutenanceId()"
        );
    }

    // Endpoint GET - retourne une erreur claire car la méthode n'existe PAS
    @GetMapping("/soutenance/{soutenanceId}/enseignant/{enseignantId}")
    public ResponseEntity<?> getNoteByJury(
            @PathVariable int soutenanceId,
            @PathVariable int enseignantId) {
        return ResponseEntity.status(501).body(
                "Cette fonctionnalité n'est pas encore implémentée. " +
                        "Méthode manquante dans NoteService: getNoteBySoutenanceAndEnseignant()"
        );
    }
}