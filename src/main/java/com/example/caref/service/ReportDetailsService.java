package com.example.caref.service;

import com.example.caref.execption.ResourceNotFoundException;
import com.example.caref.models.Report;
import com.example.caref.models.User;
import com.example.caref.models.dto.ReportDto;
import com.example.caref.models.dto.ReportResponseDto;
import com.example.caref.repository.CarRepository;
import com.example.caref.repository.ReportRepository;
import com.example.caref.repository.UserRepository;
import com.example.caref.security.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ReportDetailsService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarRepository carRepository;



    public ReportResponseDto save(ReportDto report) {
        User user = userRepository.getOne(SecurityUtils.getCurrentUserId());
        //Car car = carRepository.findById(report.getCarId());
        // Car car = carRepository.getOne(report.getCarId());
        Report newReport = new Report();
        newReport.setReportContenue(report.getReportContenue());
        newReport.setDate(new Date());
        newReport.setReportName(report.getReportName());
        newReport.setUser(user);
        //newReport.setCar(car);

        newReport = reportRepository.save(newReport);

        return findOneReportById(newReport.getId());
    }

    public ReportResponseDto findOneReportById(Long reportId) {
        Report report = reportRepository.getOne(reportId);
        Optional<User> optionalUser = userRepository.findByUsername(report.getCreatedBy());
        return ReportResponseDto.ReportResponseDtoBuilder.aReportResponseDto()
                .withId(reportId)
                .withdateInsertion(report.getDate())
                .withReportContenue(report.getReportContenue())
                .withreportName(report.getReportName())
                .withversion(report.getVersion())
                .withcreatedDate(report.getCreatedDate())
                .withmodifiedBy(report.getModifiedBy())
                .withmodifiedDate(report.getModifiedDate())
                .withPostByName(optionalUser.map(User::getUsername).orElse(null))
                .build();
    }

    public ReportResponseDto updateReport(Long reportId, ReportDto reportDto) throws ResourceNotFoundException {
        Report report = reportRepository.findById(reportId).orElseThrow(() -> new ResourceNotFoundException("Report not found for this id:: " +reportId));

        if(Objects.nonNull(reportDto.getReportContenue())) {
            report.setReportContenue(reportDto.getReportContenue());
        }
        Report finalNewReport = report;
        report = reportRepository.save(report);
        return findOneReportById(report.getId());
    }

    public List<ReportResponseDto> findAllReport() {
        return reportRepository.findAll().stream().map(buildListReportResponseDto()).collect(Collectors.toList());
    }

    private Function<Report, ReportResponseDto> buildListReportResponseDto() {
        return report -> findOneReportById(report.getId());
    }

    public List<Report> findAllReportBy(Long userId) {
        return reportRepository.findAllByUser(userRepository.getOne(userId));
    }
}

