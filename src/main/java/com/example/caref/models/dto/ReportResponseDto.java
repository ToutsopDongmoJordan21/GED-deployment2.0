package com.example.caref.models.dto;

import lombok.Data;

import java.util.Calendar;
import java.util.Date;

@Data
public class ReportResponseDto {
    private Long id;
    private String reportContenue;
    private String reportName;
    private String postByName;
    private Date dateInsertion;
    private Long version;
    private Calendar createdDate;
    private String modifiedBy;
    private Calendar modifiedDate;

    public static final class ReportResponseDtoBuilder {
        private Long id;
        private String reportContenue;
        private String postByName;
        private Date dateInsertion;
        private String reportName;
        private Long version;
        private Calendar createdDate;
        private String modifiedBy;
        private Calendar modifiedDate;


        private ReportResponseDtoBuilder() {
        }

        public static ReportResponseDtoBuilder aReportResponseDto() {

            return new ReportResponseDtoBuilder();
        }

        public ReportResponseDtoBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public ReportResponseDtoBuilder withreportName(String reportName) {
            this.reportName = reportName;
            return this;
        }

        public ReportResponseDtoBuilder withdateInsertion(Date dateInsertion) {
            this.dateInsertion = dateInsertion;
            return this;
        }

        public ReportResponseDtoBuilder withversion(Long version) {
            this.version = version;
            return this;
        }

        public ReportResponseDtoBuilder withcreatedDate(Calendar createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public ReportResponseDtoBuilder withmodifiedBy(String modifiedBy) {
            this.modifiedBy = modifiedBy;
            return this;
        }

        public ReportResponseDtoBuilder withmodifiedDate(Calendar modifiedDate) {
            this.modifiedDate = modifiedDate;
            return this;
        }

        public ReportResponseDtoBuilder withPostByName(String postByName) {
            this.postByName = postByName;
            return this;
        }


        public ReportResponseDtoBuilder withReportContenue(String reportContenue) {
            this.reportContenue = reportContenue;
            return this;
        }

        public ReportResponseDto build() {
            ReportResponseDto reportResponseDto = new ReportResponseDto();
            reportResponseDto.setId(id);
            reportResponseDto.setReportContenue(reportContenue);
            reportResponseDto.setReportName(reportName);
            reportResponseDto.setDateInsertion(dateInsertion);
            reportResponseDto.setVersion(version);
            reportResponseDto.setCreatedDate(createdDate);
            reportResponseDto.setModifiedBy(modifiedBy);
            reportResponseDto.setModifiedDate(modifiedDate);
            reportResponseDto.setPostByName(postByName);
            return reportResponseDto;
        }
    }
}

