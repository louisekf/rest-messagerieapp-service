package com.springboot.restservice.messagerieapp.dto;

public class MailMessageDTO<T> extends RestApiResponse<T>{
    private Long id;
    private String expediteur;
    private String destinataire;
    private String objet;
    private String contenu;
    private boolean lu;

}
