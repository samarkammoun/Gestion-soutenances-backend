package com.example.GestionSoutenances.repositories;

import com.example.GestionSoutenances.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepo extends JpaRepository<Note, Integer> {
}
