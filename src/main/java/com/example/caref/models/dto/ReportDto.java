package com.example.caref.models.dto;

import java.util.List;

public class ReportDto {

    private List<Long> user;

    private List<Long> car;

    /*public Long getCarId() {
        return carId;
    }

    public ReportDto setCarId(Long carId) {
        this.carId = carId;
        return this;
    }

    private Long carId;*/

    public String getReportContenue() {
        return reportContenue;
    }

    public void setReportContenue(String reportContenue) {
        this.reportContenue = reportContenue;
    }

    private String reportContenue;

    public String getReportName() {
        return reportName;
    }

    public ReportDto setReportName(String reportName) {
        this.reportName = reportName;
        return this;
    }

    private String reportName;

    public ReportDto(String reportContenue) {
        this.reportContenue = reportContenue;
    }

    public ReportDto() {}

}

