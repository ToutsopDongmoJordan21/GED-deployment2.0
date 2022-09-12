package com.example.caref.models.dto;

import com.example.caref.files.dto.FileDto;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class FichierResponseDto {

    private  long id;
    private String fichierTitle;
    private Date fichierAddedDate;
    private String postByName;
    private Long postById;
    private List<FileDto> fichierImages;


    public static final class FichierResponseDtoBuilder {
        private  long id;
        private String fichierTitle;
        private Date fichierAddedDate;

        private List<FileDto> fichierImages;


        private String postByName;
        private Long postById;

        private FichierResponseDtoBuilder() {}

        public static FichierResponseDtoBuilder aFichierResponseDto() {
            return new FichierResponseDtoBuilder();
        }

        public FichierResponseDtoBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public FichierResponseDtoBuilder withFichierTitle(String fichierTitle) {
            this.fichierTitle = fichierTitle;
            return this;
        }

        public FichierResponseDtoBuilder withFichierAddedDate(Date fichierAddedDate) {
            this.fichierAddedDate = fichierAddedDate;
            return this;
        }

        public FichierResponseDtoBuilder withPostByName(String postByName) {
            this.postByName = postByName;
            return this;
        }

        public FichierResponseDtoBuilder withPostById(Long postById) {
            this.postById = postById;
            return this;
        }

        public FichierResponseDtoBuilder withFichierImage(List<FileDto> fichierImages) {
            this.fichierImages = fichierImages;
            return this;
        }

        public FichierResponseDto build() {
            FichierResponseDto fichierResponseDto = new FichierResponseDto();
            fichierResponseDto.setId(id);
            fichierResponseDto.setFichierTitle(fichierTitle);
            fichierResponseDto.setFichierAddedDate(fichierAddedDate);
            fichierResponseDto.setPostByName(postByName);
            fichierResponseDto.setPostById(postById);

            return fichierResponseDto;
        }
    }
}
