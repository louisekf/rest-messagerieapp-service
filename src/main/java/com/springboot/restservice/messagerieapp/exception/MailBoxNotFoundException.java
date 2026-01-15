package com.springboot.restservice.messagerieapp.exception;

public class MailBoxNotFoundException extends RuntimeException {
    public MailBoxNotFoundException(String message) {
        super(message);
    }
}
