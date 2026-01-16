package com.springboot.restservice.messagerieapp.exception;

public class MailMessageNotFoundException extends RuntimeException {
    public MailMessageNotFoundException(String message) {
        super(message);
    }
    public MailMessageNotFoundException(Long id) {
        super("Message avec l'ID " + id + " introuvable");
    }
}
