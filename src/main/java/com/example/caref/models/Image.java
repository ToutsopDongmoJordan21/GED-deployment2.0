package com.example.caref.models;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "image")
public class Image {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max=255)
    private String name;

    @Size(max=255)
    private String type;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    private String path;

    //    @Column(name="picByte", length = 1000)
    @Transient
    private MultipartFile picByte;

    //j'ai ajouté ici

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id",
            referencedColumnName = "id")
    private Car car;

    //j'ai ajouter ici
    public Image(Long id, String name, String type, MultipartFile picByte, String path) {
        this.id = id;
        this.path = path;
        this.name = name;
        this.type = type;
        this.picByte = picByte;
    }

    public Image(Object originalFilename, String contentType, byte[] compressBytes) {
        this.type = contentType;
    }

    public Image() { }


    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public MultipartFile getPicByte() { return picByte; }

    public void setPicByte(MultipartFile picByte) { this.picByte = picByte; }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}

