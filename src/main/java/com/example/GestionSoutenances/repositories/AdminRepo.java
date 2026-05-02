package com.example.GestionSoutenances.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.GestionSoutenances.entities.Admin;
import java.util.Optional;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Integer> {
    Optional<Admin> findByLogin(String login);
}
