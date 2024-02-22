package com.ensolvers.backend.Exceptions;

public class NoteNotFound extends RuntimeException {
    public NoteNotFound(String message) {
        super(message);
    }
}
