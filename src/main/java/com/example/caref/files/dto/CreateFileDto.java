package com.example.caref.files.dto;


import com.example.caref.files.entities.enumeration.DocType;
import com.example.caref.files.entities.enumeration.EntityFileType;
import com.example.caref.files.entities.enumeration.FileType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Copyright (c) 2020, KTACENT, All Right Reserved.
 * https://www.linkedin.com/in/alex-kouasseu/
 * <p>
 * When : 07/11/2020 -- 13:14
 * By : @author Toutsop Jordan
 * Project : lp-user-service
 * Package : com.lukapharma.api.files.dto
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateFileDto {
    @NotNull
    private DocType type;
    @NotNull
    private EntityFileType entity;
    @NotNull
    private FileType fileType;
    private Long carId;
    private Long garageId;
    private Long userId;
}
