package com.ensolvers.backend.Controllers;
import com.ensolvers.backend.Entities.Note;
import com.ensolvers.backend.Exceptions.AppException;
import com.ensolvers.backend.Services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class NoteController {
    @Autowired
    NoteService noteService;

    @PostMapping("create-note")
    public ResponseEntity<?> createNote (@RequestBody Note note) throws AppException {
        try {
            noteService.saveNote(note);
            return new ResponseEntity<>("Saved!", HttpStatus.OK);
        } catch (Exception e) {
            throw new AppException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("get-unarchived")
    public ResponseEntity<List<Note>> getUnarchivedNotes () throws AppException {
        try {
            return new ResponseEntity<>(noteService.getUnarchivedNotes(), HttpStatus.OK);
        } catch (Exception e) {
            throw new AppException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("get-archived")
    public ResponseEntity<List<Note>> getArchivedNotes () throws AppException {
        try {
            return new ResponseEntity<>(noteService.getArchivedNotes(), HttpStatus.OK);
        } catch (Exception e) {
            throw new AppException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("get-note/{id}")
    public ResponseEntity<?> getNote (@PathVariable String id) throws AppException {
        try {
            Note toEdit = noteService.getNote(id);
            return new ResponseEntity<>(toEdit, HttpStatus.OK);
        } catch (Exception e) {
            throw new AppException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("edit-note/{id}")
    public ResponseEntity<?> editNote (@PathVariable String id, @RequestBody Note note) throws AppException {
        try {
            noteService.updateNote(id, note);
            return new ResponseEntity<>("Saved!", HttpStatus.OK);
        } catch (Exception e) {
            throw new AppException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("delete-note/{id}")
    public ResponseEntity<?> deleteNote (@PathVariable String id) throws AppException {
        try {
            noteService.deleteNote(id);
            return new ResponseEntity<>("Note deleted!", HttpStatus.OK);
        } catch (Exception e) {
            throw new AppException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("archive-note/{id}")
    public ResponseEntity<?> archiveNote (@PathVariable String id) throws AppException {
        try {
            noteService.archiveNote(id);
            return new ResponseEntity<>("Note archived!", HttpStatus.OK);
        } catch (Exception e) {
            throw new AppException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("unarchive-note/{id}")
    public ResponseEntity<?> unarchiveNote (@PathVariable String id) throws AppException {
        try {
            noteService.unarchiveNote(id);
            return new ResponseEntity<>("Note unarchived!", HttpStatus.OK);
        } catch (Exception e) {
            throw new AppException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
