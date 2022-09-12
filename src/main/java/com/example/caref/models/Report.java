package com.example.caref.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Report extends AbstractIforce5Audit{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String getReportContenue() {
        return reportContenue;
    }

    public void setReportContenue(String reportContenue) {
        this.reportContenue = reportContenue;
    }

    @Size(max = 255)
    private String reportContenue;

    public String getReportName() {
        return reportName;
    }

    public Report setReportName(String reportName) {
        this.reportName = reportName;
        return this;
    }

    private String reportName;

    @DateTimeFormat
    private Date date;

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

/*    public Car getCar() {
        return car;
    }

    public Report setCar(Car car) {
        this.car = car;
        return this;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id",
            referencedColumnName = "id")
    private Car car;*/

    public Report() {
    }

    public Report(String reportContenue, Date date) {
        this.reportContenue = reportContenue;
        this.date = date;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Date getDate() { return date; }

    public void setDate(Date date) { this.date = date; }

}

