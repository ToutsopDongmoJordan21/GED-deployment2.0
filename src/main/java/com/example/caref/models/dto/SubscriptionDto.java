package com.example.caref.models.dto;

import java.util.Date;

public class SubscriptionDto {

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    private String price;

    private String subscriptionType;

    private Date dateDebut;

    private Date dateFin;


    public SubscriptionDto(String price,
                           String subscriptionType,
                           Date dateDebut,
                           Date dateFin) {
        this.price = price;
        this.subscriptionType = subscriptionType;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }
}
