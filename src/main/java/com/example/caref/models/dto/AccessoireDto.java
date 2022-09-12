package com.example.caref.models.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AccessoireDto {
    @NotBlank(message="enter accessoire name")
    @Size(max=255)
    private String accessoireName;

    public AccessoireDto(String accessoireName) {
        this.accessoireName = accessoireName;
    }
    public AccessoireDto() {}


    public String getAccessoireName() { return accessoireName; }

    public void setAccessoireName(String accessoireName) {
        this.accessoireName = accessoireName;
    }
}
