package com.springboot.restservice.messagerieapp.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mailboxes")
public class MailBox {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String proprietaire;
    private double capaciteMax;
    private double espaceUtilise;
    private String adresseEmail;
    public MailBox() {}

    @OneToMany(mappedBy = "mailbox", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MailMessage> messages = new ArrayList<>();


    public MailBox(String proprietaire, double capaciteMax, String adresseEmail) {
        this.proprietaire = proprietaire;
        this.capaciteMax = capaciteMax;
        this.adresseEmail = adresseEmail;
        this.espaceUtilise = 0.0;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(String proprietaire) {
        this.proprietaire = proprietaire;
    }

    public double getCapaciteMax() {
        return capaciteMax;
    }

    public void setCapaciteMax(double capaciteMax) {
        this.capaciteMax = capaciteMax;
    }

    public double getEspaceUtilise() {
        return espaceUtilise;
    }

    public void setEspaceUtilise(double espaceUtilise) {
        this.espaceUtilise = espaceUtilise;
    }

    public String getAdresseEmail() {
        return adresseEmail;
    }

    public void setAdresseEmail(String adresseEmail) {
        this.adresseEmail = adresseEmail;
    }

    public List<MailMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<MailMessage> messages) {
        this.messages = messages;
    }

    public void addMessage(MailMessage message) {
        this.messages.add(message);
        message.setMailBox(this);
    }
}
