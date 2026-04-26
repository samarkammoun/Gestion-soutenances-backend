package com.example.GestionSoutenances.services;

import com.example.GestionSoutenances.entities.Salle;
import com.example.GestionSoutenances.repositories.SalleRepo;
import com.example.GestionSoutenances.repositories.SoutenanceRepo;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SalleService {

    private final SalleRepo salleRepository;
    private final SoutenanceRepo soutenanceRepository;

    public Salle createSalle(Salle s) {
        return salleRepository.save(s);
    }

    public List<Salle> getAllSalles() {
        return salleRepository.findAll();
    }

    public List<Salle> getSallesDisponibles(LocalDate date, LocalTime debut, LocalTime fin) {
        return salleRepository.findSallesDisponibles(date, debut, fin);
    }
}
