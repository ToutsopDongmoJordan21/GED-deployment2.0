package com.example.caref.models.dto;


import lombok.Data;

@Data
public class AccessoireResponseDto {
    private Long id;
    private String accessoireName;

    public static final class AccessoireResponseDtoBuilder {
        private Long id;
        private String accessoireName;

        private AccessoireResponseDtoBuilder() {}

        public static AccessoireResponseDtoBuilder aAccessoireResponseDto() {
            return new AccessoireResponseDtoBuilder();
        }

        public AccessoireResponseDtoBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public AccessoireResponseDtoBuilder withAccessoireName(String accessoireName) {
            this.accessoireName = accessoireName;
            return this;
        }

        public AccessoireResponseDto build() {
            AccessoireResponseDto accessoireResponseDto = new AccessoireResponseDto();
            accessoireResponseDto.setId(id);
            accessoireResponseDto.setAccessoireName(accessoireName);
            return accessoireResponseDto;
        }
    }
}

