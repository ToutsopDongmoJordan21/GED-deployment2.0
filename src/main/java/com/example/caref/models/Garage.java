package com.example.caref.models;

import javax.persistence.*;

@Entity
@Table( name = "garage")
public class Garage {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGarageName() {
        return garageName;
    }

    public void setGarageName(String garageName) {
        this.garageName = garageName;
    }

    public String getGarageAddress() {
        return garageAddress;
    }

    public void setGarageAddress(String garageAddress) {
        this.garageAddress = garageAddress;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String garageName;

    private String garageAddress;

    public Garage() {}

    public Garage(Long id,
                  String garageName,
                  String garageAddress) {
        this.id = id;
        this.garageName = garageName;
        this.garageAddress = garageAddress;
    }

}
