package com.example.caref.models.dto;

public class GarageDto {

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

    public GarageDto(String garageName, String garageAddress) {
        this.garageName = garageName;
        this.garageAddress = garageAddress;
    }

    public GarageDto() {}

    private String garageName;

    private String garageAddress;
}
