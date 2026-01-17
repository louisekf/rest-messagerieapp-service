package com.springboot.restservice.messagerieapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class MailMessageDTO{
    private Long id;

    @NotBlank(message = "L'expéditeur est obligatoire")
    @Email(message = "L'adresse de l'expéditeur doit être valide")
    private String expediteur;

    @NotBlank(message = "Le destinataire est obligatoire")
    @Email(message = "L'adresse du destinataire doit être valide")
    private String destinataire;

    @NotBlank(message = "L'objet est obligatoire")
    private String objet;

    @NotBlank(message = "Le contenu est obligatoire")
    private String contenu;
    private LocalDateTime dateEnvoi;
    private Boolean lu;

    public MailMessageDTO() {
    }

    public MailMessageDTO(Long id, String expediteur, String destinataire, String objet, String contenu, LocalDateTime dateEnvoi, Boolean lu) {
        this.id = id;
        this.expediteur = expediteur;
        this.destinataire = destinataire;
        this.objet = objet;
        this.contenu = contenu;
        this.dateEnvoi = dateEnvoi;
        this.lu = lu;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExpediteur() {
        return expediteur;
    }

    public void setExpediteur(String expediteur) {
        this.expediteur = expediteur;
    }

    public String getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(String destinataire) {
        this.destinataire = destinataire;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public LocalDateTime getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(LocalDateTime dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    public Boolean getLu() {
        return lu;
    }

    public void setLu(Boolean lu) {
        this.lu = lu;
    }
}

