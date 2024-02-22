package com.ensolvers.backend.Repositories;

import com.ensolvers.backend.Entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, String> {
    List<Note> findByStateTrue();
    List<Note> findByStateFalse();

    @Query("SELECT DISTINCT n.category FROM Note n WHERE n.state = true AND n.category IS NOT NULL AND n.category <> ''")
    List<String> findAllCategories();
}
