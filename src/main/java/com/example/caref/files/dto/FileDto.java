package com.example.caref.files.dto;

import com.example.caref.files.entities.enumeration.DocType;
import com.example.caref.files.entities.enumeration.FileType;
import lombok.Data;

@Data
public class FileDto {

    private Long id;

    private DocType type;

    private FileType fileType;

    private Long cardId;

    private Long garageCode;

    private Long userId;

    private String fileName;

    private String url;


    public static final class FileDtoBuilder {
        private Long id;
        private DocType type;
        private FileType fileType;
        private Long cardId;
        private Long garageCode;
        private Long userId;
        private String fileName;
        private String url;

        private FileDtoBuilder() {
        }

        public static FileDtoBuilder aFileDto() {
            return new FileDtoBuilder();
        }

        public FileDtoBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public FileDtoBuilder withType(DocType type) {
            this.type = type;
            return this;
        }

        public FileDtoBuilder withFileType(FileType fileType) {
            this.fileType = fileType;
            return this;
        }

        public FileDtoBuilder withCardId(Long cardId) {
            this.cardId = cardId;
            return this;
        }

        public FileDtoBuilder withGarageCode(Long garageCode) {
            this.garageCode = garageCode;
            return this;
        }

        public FileDtoBuilder withUserId(Long userId) {
            this.userId = userId;
            return this;
        }

        public FileDtoBuilder withFileName(String fileName) {
            this.fileName = fileName;
            return this;
        }

        public FileDtoBuilder withUrl(String url) {
            this.url = url;
            return this;
        }

        public FileDto build() {
            FileDto fileDto = new FileDto();
            fileDto.setId(id);
            fileDto.setType(type);
            fileDto.setFileType(fileType);
            fileDto.setCardId(cardId);
            fileDto.setGarageCode(garageCode);
            fileDto.setUserId(userId);
            fileDto.setFileName(fileName);
            fileDto.setUrl(url);
            return fileDto;
        }
    }
}
