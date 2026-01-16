package com.springboot.restservice.messagerieapp.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Table(name = "mail_messages")
public class MailMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String expediteur;
    private String destinataire;
    private String objet;
    private String contenu;
    private LocalDateTime dateEnvoi;
    private boolean lu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mailbox_id")
    private MailBox mailbox;

    public MailMessage() {
        this.dateEnvoi = LocalDateTime.now();
        this.lu = false;
    }

    public MailMessage(String expediteur, String destinataire, String objet, String contenu) {
        this();
        this.expediteur = expediteur;
        this.destinataire = destinataire;
        this.objet = objet;
        this.contenu = contenu;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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

    public boolean isLu() {
        return lu;
    }

    public void setLu(boolean lu) {
        this.lu = lu;
    }

    public MailBox getMailbox() {
        return mailbox;
    }

    public void setMailBox(MailBox mailBox) {
        this.mailbox = mailBox;
    }
    public double getTailleEnMo() {
        int tailleBytes = (expediteur.length() + destinataire.length() +
                objet.length() + contenu.length()) * 2; // UTF-16
        return tailleBytes / (1024.0 * 1024.0);
    }
}
