package edu.school21.chat.app;

public class NotSavedSubEntityException extends RuntimeException {
    public NotSavedSubEntityException() {
        super("author and room have no ID existing in the database assigned, or these IDs are null");
    }
}