package com.example.caref.models.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CarDto {

    @NotNull
    private String carTitle;

    @NotNull
    private String carBrand;

    private String carOverview;

    @NotNull
    private String carLoanPrice;

    @NotNull
    private String carFuel;

    private String carYearModel;

    private String carSeating;

    @NotNull
    private String carPrice;

    private List<Long> accessors;


}
