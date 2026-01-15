package com.springboot.restservice.messagerieapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MailBox {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String proprietaire;
    private double capaciteMax;
    private double espaceUtilise;
    private String adresseEmail;
    public MailBox() {}

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
}
