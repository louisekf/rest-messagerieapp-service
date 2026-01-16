package com.springboot.restservice.messagerieapp.exception;

public class QuotaExceededException extends RuntimeException {
    public QuotaExceededException(String message) {
        super(message);
    }

    public QuotaExceededException(String adresseEmail, double tailleMessage, double espaceDisponible) {
        super(String.format(
                "Quota dépassé pour la boîte mail %s. Taille du message: %.2f Mo, Espace disponible: %.2f Mo",
                adresseEmail, tailleMessage, espaceDisponible
        ));
    }
}
