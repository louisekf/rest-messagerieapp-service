package com.springboot.restservice.messagerieapp.dto;

public class QuotaDTO {
    private Double capaciteMax;
    private Double espaceUtilise;
    private Double pourcentageUtilisation;

    // Constructeur, Getters et Setters

    public QuotaDTO() {
    }

    public QuotaDTO(Double capaciteMax, Double espaceUtilise, Double pourcentageUtilisation) {
        this.capaciteMax = capaciteMax;
        this.espaceUtilise = espaceUtilise;
        this.pourcentageUtilisation = pourcentageUtilisation;
    }

    public Double getCapaciteMax() {
        return capaciteMax;
    }

    public void setCapaciteMax(Double capaciteMax) {
        this.capaciteMax = capaciteMax;
    }

    public Double getEspaceUtilise() {
        return espaceUtilise;
    }

    public void setEspaceUtilise(Double espaceUtilise) {
        this.espaceUtilise = espaceUtilise;
    }

    public Double getPourcentageUtilisation() {
        return pourcentageUtilisation;
    }

    public void setPourcentageUtilisation(Double pourcentageUtilisation) {
        this.pourcentageUtilisation = pourcentageUtilisation;
    }
}
