package com.springboot.restservice.messagerieapp.exception;

public class MailMessageNotFoundException extends RuntimeException {
    public MailMessageNotFoundException(String message) {
        super(message);
    }
}
