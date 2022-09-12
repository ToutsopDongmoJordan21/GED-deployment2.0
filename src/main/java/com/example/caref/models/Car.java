package com.example.caref.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table( name = "cars")
public class Car extends AbstractIforce5Audit{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Size(max = 30)
    private String carTitle;

    @Size(max = 40)
    private String carBrand;

    private String carOverview;

    private String carLoanPrice;

    private String carFuel;

    private String carYearModel;

    private String carSeating;

    private String carPrice;

    public void setCarAddedDate(Date carAddedDate) {
        this.carAddedDate = carAddedDate;
    }

    @DateTimeFormat
    private Date carAddedDate;

    public Car() {
    }

    @OneToMany(
            mappedBy = "car",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
    private Set<Image> carImage = new HashSet<>();

    @OneToMany
    private Set<CarAccessoire> carAccessoire = new HashSet<>();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",
            referencedColumnName = "id")
    private User user;


    public Car(Long id,
               String carTitle,
               String carBrand,
               String carOverview,
               String carLoanPrice,
               String carFuel,
               String carYearModel,
               String carSeating,
               String carPrice,
               Date carAddedDate) {
        this.id = id;
        this.carTitle = carTitle;
        this.carBrand = carBrand;
        this.carOverview = carOverview;
        this.carLoanPrice = carLoanPrice;
        this.carFuel = carFuel;
        this.carYearModel = carYearModel;
        this.carSeating = carSeating;
        this.carPrice = carPrice;
        this.carAddedDate = carAddedDate;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getCarTitle() { return carTitle; }

    public void setCarTitle(String carTitle) {
        this.carTitle = carTitle;
    }

    public String getCarBrand() { return carBrand; }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarOverview() { return carOverview; }

    public void setCarOverview(String carOverview) {
        this.carOverview = carOverview;
    }

    public String getCarLoanPrice() { return carLoanPrice; }

    public void setCarLoanPrice(String carLoanPrice) {
        this.carLoanPrice = carLoanPrice;
    }

    public String getCarFuel() { return carFuel; }

    public void setCarFuel(String carFuel) {
        this.carFuel = carFuel;
    }

    public String getCarYearModel() { return carYearModel; }

    public void setCarYearModel(String carYearModel) {
        this.carYearModel = carYearModel;
    }

    public String getCarSeating() { return carSeating; }

    public void setCarSeating(String carSeating) {
        this.carSeating = carSeating;
    }

    public String getCarPrice() { return carPrice; }

    public void setCarPrice(String carPrice) {
        this.carPrice = carPrice;
    }

    public Date getCarAddedDate(Date date) { return carAddedDate; }


    public Set<CarAccessoire> getCarAccessoire() { return carAccessoire; }

    public Set<Image> getCarImage() {
        return carImage;
    }

    public void setCarImage(Set<Image> carImage) {
        this.carImage = carImage;
    }

    public void setCarAccessoire(Set<CarAccessoire> carAccessoire) {
        this.carAccessoire = carAccessoire;
    }
}

