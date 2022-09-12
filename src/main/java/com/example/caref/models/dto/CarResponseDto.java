package com.example.caref.models.dto;

import com.example.caref.files.dto.FileDto;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CarResponseDto {
    private Long id;
    private String carTitle;
    private String carBrand;
    private String carOverview;
    private String carLoanPrice;
    private String carFuel;
    private String carYearModel;
    private String carSeating;
    private String carPrice;
    private Date carAddedDate;
    private String postByName;
    private Long postById;
    private List<Long> accessorsId;
    private List<String> accessorsName;
    private List<FileDto> carImages;

    public static final class CarResponseDtoBuilder {
        private Long id;
        private String carTitle;
        private String carBrand;
        private String carOverview;
        private String carLoanPrice;
        private String carFuel;
        private String carYearModel;
        private String carSeating;
        private String carPrice;
        private Date carAddedDate;
        private String postByName;
        private Long postById;
        private List<Long> accessorsId;
        private List<String> accessorsName;
        private List<FileDto> carImages;

        private CarResponseDtoBuilder() {
        }

        public static CarResponseDtoBuilder aCarResponseDto() {
            return new CarResponseDtoBuilder();
        }

        public CarResponseDtoBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public CarResponseDtoBuilder withCarTitle(String carTitle) {
            this.carTitle = carTitle;
            return this;
        }

        public CarResponseDtoBuilder withCarBrand(String carBrand) {
            this.carBrand = carBrand;
            return this;
        }

        public CarResponseDtoBuilder withCarOverview(String carOverview) {
            this.carOverview = carOverview;
            return this;
        }

        public CarResponseDtoBuilder withCarLoanPrice(String carLoanPrice) {
            this.carLoanPrice = carLoanPrice;
            return this;
        }

        public CarResponseDtoBuilder withCarFuel(String carFuel) {
            this.carFuel = carFuel;
            return this;
        }

        public CarResponseDtoBuilder withCarYearModel(String carYearModel) {
            this.carYearModel = carYearModel;
            return this;
        }

        public CarResponseDtoBuilder withCarSeating(String carSeating) {
            this.carSeating = carSeating;
            return this;
        }

        public CarResponseDtoBuilder withCarPrice(String carPrice) {
            this.carPrice = carPrice;
            return this;
        }

        public CarResponseDtoBuilder withCarAddedDate(Date carAddedDate) {
            this.carAddedDate = carAddedDate;
            return this;
        }

        public CarResponseDtoBuilder withPostByName(String postByName) {
            this.postByName = postByName;
            return this;
        }

        public CarResponseDtoBuilder withPostById(Long postById) {
            this.postById = postById;
            return this;
        }

        public CarResponseDtoBuilder withAccessorsId(List<Long> accessorsId) {
            this.accessorsId = accessorsId;
            return this;
        }

        public CarResponseDtoBuilder withAccessorsName(List<String> accessorsName) {
            this.accessorsName = accessorsName;
            return this;
        }

        public CarResponseDtoBuilder withCarImages(List<FileDto> carImages) {
            this.carImages = carImages;
            return this;
        }

        public CarResponseDto build() {
            CarResponseDto carResponseDto = new CarResponseDto();
            carResponseDto.setId(id);
            carResponseDto.setCarTitle(carTitle);
            carResponseDto.setCarBrand(carBrand);
            carResponseDto.setCarOverview(carOverview);
            carResponseDto.setCarLoanPrice(carLoanPrice);
            carResponseDto.setCarFuel(carFuel);
            carResponseDto.setCarYearModel(carYearModel);
            carResponseDto.setCarSeating(carSeating);
            carResponseDto.setCarPrice(carPrice);
            carResponseDto.setCarAddedDate(carAddedDate);
            carResponseDto.setPostByName(postByName);
            carResponseDto.setPostById(postById);
            carResponseDto.setAccessorsId(accessorsId);
            carResponseDto.setAccessorsName(accessorsName);
            carResponseDto.setCarImages(carImages);
            return carResponseDto;
        }
    }
}

