package com.example.GestionSoutenances.services;

import com.example.GestionSoutenances.entities.Admin;
import com.example.GestionSoutenances.repositories.AdminRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {
    
    private final AdminRepo adminRepository;

    
    public Admin authenticate(String login, String password) {
        Admin admin = adminRepository.findByLogin(login)
                .orElseThrow(() -> new EntityNotFoundException("Login ou mot de passe incorrect"));
        
        if (!admin.getPassword().equals(password)) {
            throw new EntityNotFoundException("Login ou mot de passe incorrect");
        }
        
        return admin;
    }

   
    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

  
    public Admin getAdmin(int id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Admin introuvable"));
    }
}
