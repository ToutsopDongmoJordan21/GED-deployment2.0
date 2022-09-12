package com.example.caref.files.entities;

import com.example.caref.files.entities.enumeration.DocType;
import com.example.caref.files.entities.enumeration.FileType;
import com.example.caref.models.Car;
import com.example.caref.models.Garage;
import com.example.caref.models.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "caref_files")
@Data
@ToString
public class CarefFile implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String url;
    @Enumerated(EnumType.STRING)
    private DocType type;
    @Enumerated(EnumType.STRING)
    private FileType fileType;
    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Car car;
    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Garage garage;
    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public static final class CarefFileBuilder {
        private Long id;
        private String code;
        private String fileName;
        private String url;
        private DocType type;
        private FileType fileType;
        private Car car;
        private Garage garage;
        private User user;

        private CarefFileBuilder() {
        }

        public static CarefFileBuilder aCarefFile() {
            return new CarefFileBuilder();
        }

        public CarefFileBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public CarefFileBuilder withFileName(String fileName) {
            this.fileName = fileName;
            return this;
        }

        public CarefFileBuilder withUrl(String url) {
            this.url = url;
            return this;
        }

        public CarefFileBuilder withType(DocType type) {
            this.type = type;
            return this;
        }

        public CarefFileBuilder withFileType(FileType fileType) {
            this.fileType = fileType;
            return this;
        }

        public CarefFileBuilder withCar(Car car) {
            this.car = car;
            return this;
        }

        public CarefFileBuilder withGarage(Garage garage) {
            this.garage = garage;
            return this;
        }

        public CarefFileBuilder withUser(User user) {
            this.user = user;
            return this;
        }

        public CarefFile build() {
            CarefFile carefFile = new CarefFile();
            carefFile.setId(id);
            carefFile.setFileName(fileName);
            carefFile.setUrl(url);
            carefFile.setType(type);
            carefFile.setFileType(fileType);
            carefFile.setCar(car);
            carefFile.setGarage(garage);
            carefFile.setUser(user);
            return carefFile;
        }
    }
}


