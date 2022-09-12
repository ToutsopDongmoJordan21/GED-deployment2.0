package com.example.caref.models.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class FichierDto {
    @NotNull
    private String fichierTitle;

}
