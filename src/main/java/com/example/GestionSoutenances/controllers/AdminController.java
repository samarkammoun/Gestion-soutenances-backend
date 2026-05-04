package com.example.GestionSoutenances.controllers;

import com.example.GestionSoutenances.entities.Admin;
import com.example.GestionSoutenances.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        try {
            String login = credentials.get("login");
            String password = credentials.get("password");
            Admin admin = adminService.authenticate(login, password);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Authentification réussie");
            response.put("admin", admin);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}