package com.ensolvers.backend.Services;

import com.ensolvers.backend.Entities.Note;
import com.ensolvers.backend.Exceptions.AppException;
import com.ensolvers.backend.Exceptions.NoteNotFound;
import com.ensolvers.backend.Repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {
    @Autowired
    NoteRepository noteRepository;

    public void saveNote (Note note) {
        try {
            Note newNote = new Note();
            newNote.setTitle(note.getTitle());
            newNote.setContent(note.getContent());
            newNote.setState(true);
            if (note.getCategory() != null || note.getCategory() == "") {
                newNote.setCategory(note.getCategory());
            } else {
                newNote.setCategory("General");
            }
            noteRepository.save(newNote);
        } catch (Exception e) {
            throw new AppException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public void updateNote (String id, Note newNote) {
        try {
            Note oldNote = noteRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Note not found!"));
            oldNote.setTitle(newNote.getTitle());
            oldNote.setContent(newNote.getContent());
            oldNote.setCategory(newNote.getCategory());
            noteRepository.save(oldNote);
        } catch (Exception e) {
            throw new AppException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public List<Note> getUnarchivedNotes () throws Exception {
        try {
            return noteRepository.findByStateTrue();
        } catch (Exception e) {
            throw new Exception(e);        }
    }

    public List<Note> getArchivedNotes () throws Exception {
        try {
            return noteRepository.findByStateFalse();
        } catch (Exception e) {
            throw new Exception(e);        }
    }

    public void deleteNote (String id) throws Exception {
        try {
            noteRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception(e);        }
    }

    public Note getNote (String id) throws Exception {
        try {
            Optional <Note> optionalNote = noteRepository.findById(id);
            if (optionalNote.isPresent()) {
                return optionalNote.get();
            } else {
                throw new NoteNotFound("Note not found with ID: " + id);
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void archiveNote (String id) throws Exception {
        try {
            Note oldNote = noteRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Note not found!"));
            oldNote.setState(false);
            noteRepository.save(oldNote);
        } catch (Exception e) {
            throw new Exception(e);        }
    }

    public void unarchiveNote (String id) throws Exception {
        try {
            Note oldNote = noteRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Note not found!"));
            oldNote.setState(true);
            noteRepository.save(oldNote);
        } catch (Exception e) {
            throw new Exception(e);        }
    }
}
