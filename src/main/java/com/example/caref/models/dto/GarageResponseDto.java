package com.example.caref.models.dto;

import com.example.caref.files.dto.FileDto;
import lombok.Data;

import java.util.List;

@Data
public class GarageResponseDto {
    private Long id;
    private String garageName;
    private String garageAddress;
    private List<FileDto> garageImages;

    public static final class GarageResponseDtoBuilder {
        private Long id;
        private String garageName;
        private String garageAddress;
        private List<FileDto> garageImages;

        private GarageResponseDtoBuilder() {
        }

        public static GarageResponseDtoBuilder aGarageResponseDto() {

            return new GarageResponseDtoBuilder();
        }

        public GarageResponseDtoBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public GarageResponseDtoBuilder withGarageName(String garageName) {
            this.garageName = garageName;
            return this;
        }

        public GarageResponseDtoBuilder withGarageAddress(String garageAddress) {
            this.garageAddress = garageAddress;
            return this;
        }

        public GarageResponseDtoBuilder withGarageImages(List<FileDto> garageImages) {
            this.garageImages = garageImages;
            return this;
        }

        public GarageResponseDto build() {
            GarageResponseDto garageResponseDto = new GarageResponseDto();
            garageResponseDto.setId(id);
            garageResponseDto.setGarageName(garageName);
            garageResponseDto.setGarageAddress(garageAddress);
            garageResponseDto.setGarageImages(garageImages);
            return garageResponseDto;
        }
    }
}
