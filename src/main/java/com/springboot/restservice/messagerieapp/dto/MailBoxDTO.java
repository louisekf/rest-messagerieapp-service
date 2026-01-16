package com.springboot.restservice.messagerieapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class MailBoxDTO {
    private Long id;

    @NotBlank(message = "L'adresse email est obligatoire")
    @Email(message = "L'email doit être valide")
    private String adresseEmail;

    @NotBlank(message = "Le propriétaire est obligatoire")
    private String proprietaire;

    @Min(value = 1, message = "La capacité maximale doit être au moins 1 Mo")
    private double capaciteMax;

    private double espaceUtilise;

    public MailBoxDTO() {
    }

    public MailBoxDTO(Long id, String adresseEmail, String proprietaire, double capaciteMax, double espaceUtilise) {
        this.id = id;
        this.adresseEmail = adresseEmail;
        this.proprietaire = proprietaire;
        this.capaciteMax = capaciteMax;
        this.espaceUtilise = espaceUtilise;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdresseEmail() {
        return adresseEmail;
    }

    public void setAdresseEmail(String adresseEmail) {
        this.adresseEmail = adresseEmail;
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
}
