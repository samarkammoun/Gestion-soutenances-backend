package com.example.GestionSoutenances.config;

import com.example.GestionSoutenances.entities.*;
import com.example.GestionSoutenances.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final AdminRepo adminRepo;
    private final EnseignantRepo enseignantRepo;
    private final EtudiantRepo etudiantRepo;
    private final SalleRepo salleRepo;
    private final DisponibiliteRepo disponibiliteRepo;

    public DatabaseSeeder(AdminRepo adminRepo, EnseignantRepo enseignantRepo,
                          EtudiantRepo etudiantRepo, SalleRepo salleRepo,
                          DisponibiliteRepo disponibiliteRepo) {
        this.adminRepo = adminRepo;
        this.enseignantRepo = enseignantRepo;
        this.etudiantRepo = etudiantRepo;
        this.salleRepo = salleRepo;
        this.disponibiliteRepo = disponibiliteRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        // Vérifier si la base est déjà remplie
        if (adminRepo.count() > 0) {
            System.out.println("Base de données déjà remplie, pas de seeding");
            return;
        }

        System.out.println("Démarrage du seeding de la base de données...");

        seedAdmins();
        seedEnseignants();
        seedEtudiants();
        seedSalles();

        System.out.println("Seeding complété avec succès!");
    }

    private void seedAdmins() {
        List<Admin> admins = new ArrayList<>();

        Admin admin1 = new Admin();
        admin1.setLogin("admin");
        admin1.setPassword("admin123");
        admins.add(admin1);

        Admin admin2 = new Admin();
        admin2.setLogin("coordinator");
        admin2.setPassword("coord456");
        admins.add(admin2);

        adminRepo.saveAll(admins);
        System.out.println("✓ " + admins.size() + " admin(s) créé(s)");
    }

    private void seedEnseignants() {
        List<Enseignant> enseignants = new ArrayList<>();

        // Enseignants avec leurs données
        String[][] enseignantsData = {
                {"Martin", "Jean", "Professeur"},
                {"Dupont", "Marie", "Maître de Conférences"},
                {"Moreau", "Pierre", "Professeur"},
                {"Lefevre", "Sophie", "Maître de Conférences"},
                {"Bernard", "Luc", "Professeur"},
                {"Dubois", "Anne", "Maître de Conférences"},
                {"Laurent", "Thomas", "Professeur"},
                {"Simon", "Nathalie", "Maître de Conférences"}
        };

        LocalDate dateBase = LocalDate.of(2026, 5, 5); // 5 mai 2026

        for (int i = 0; i < enseignantsData.length; i++) {
            Enseignant enseignant = new Enseignant();
            enseignant.setNom(enseignantsData[i][0]);
            enseignant.setPrenom(enseignantsData[i][1]);
            enseignant.setGrade(enseignantsData[i][2]);

            Enseignant saved = enseignantRepo.save(enseignant);

            // Ajouter des disponibilités pour chaque enseignant
            addDisponibilites(saved, dateBase, i);

            enseignants.add(saved);
        }

        System.out.println("✓ " + enseignants.size() + " enseignant(s) créé(s)");
    }

    private void addDisponibilites(Enseignant enseignant, LocalDate dateBase, int index) {
        List<Disponibilite> disponibilites = new ArrayList<>();

        // Chaque enseignant a plusieurs créneaux disponibles sur plusieurs jours
        for (int day = 0; day < 5; day++) { // 5 jours
            LocalDate date = dateBase.plusDays(day);

            // Créneau du matin
            Disponibilite dispo1 = new Disponibilite();
            dispo1.setDate(date);
            dispo1.setHeureDebut(LocalTime.of(9, 0));
            dispo1.setHeureFin(LocalTime.of(12, 0));
            dispo1.setEnseignant(enseignant);
            disponibilites.add(dispo1);

            // Créneau de l'après-midi
            Disponibilite dispo2 = new Disponibilite();
            dispo2.setDate(date);
            dispo2.setHeureDebut(LocalTime.of(14, 0));
            dispo2.setHeureFin(LocalTime.of(17, 0));
            dispo2.setEnseignant(enseignant);
            disponibilites.add(dispo2);
        }

        disponibiliteRepo.saveAll(disponibilites);
    }

    private void seedEtudiants() {
        List<Etudiant> etudiants = new ArrayList<>();

        String[][] etudiantsData = {
                {"Renard", "Alice", "alice.renard@university.fr"},
                {"Gauthier", "Bob", "bob.gauthier@university.fr"},
                {"Mercier", "Claire", "claire.mercier@university.fr"},
                {"Blanc", "David", "david.blanc@university.fr"},
                {"Girard", "Emma", "emma.girard@university.fr"},
                {"Durand", "Franck", "franck.durand@university.fr"},
                {"Fontaine", "Gabrielle", "gabrielle.fontaine@university.fr"},
                {"Gallo", "Henri", "henri.gallo@university.fr"},
                {"Hubert", "Irene", "irene.hubert@university.fr"},
                {"Joly", "Jacques", "jacques.joly@university.fr"},
                {"Lacroix", "Karine", "karine.lacroix@university.fr"},
                {"Leblanc", "Luc", "luc.leblanc@university.fr"}
        };

        for (String[] data : etudiantsData) {
            Etudiant etudiant = new Etudiant();
            etudiant.setNom(data[0]);
            etudiant.setPrenom(data[1]);
            etudiant.setEmail(data[2]);
            etudiants.add(etudiant);
        }

        etudiantRepo.saveAll(etudiants);
        System.out.println("✓ " + etudiants.size() + " étudiant(s) créé(s)");
    }

    private void seedSalles() {
        List<Salle> salles = new ArrayList<>();

        Object[][] sallesData = {
                {"A101", 30},
                {"A102", 40},
                {"A201", 25},
                {"A202", 35},
                {"B101", 50},
                {"B102", 30},
                {"C101", 40},
                {"C102", 20}
        };

        for (Object[] data : sallesData) {
            Salle salle = new Salle();
            salle.setNomSalle((String) data[0]);
            salle.setCapacite((Integer) data[1]);
            salles.add(salle);
        }

        salleRepo.saveAll(salles);
        System.out.println("✓ " + salles.size() + " salle(s) créée(s)");
    }
}
