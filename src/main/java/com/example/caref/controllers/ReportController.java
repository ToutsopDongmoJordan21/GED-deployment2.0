package com.example.caref.controllers;

import com.example.caref.execption.ResourceNotFoundException;
import com.example.caref.models.Report;
import com.example.caref.models.dto.ReportDto;
import com.example.caref.repository.ReportRepository;
import com.example.caref.service.ReportDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class ReportController {
    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private ReportDetailsService reportDetailsService;

    @GetMapping("/report")
    public ResponseEntity<?> getAllReports() {
        return ResponseEntity.ok(reportDetailsService.findAllReport());
    }

    @GetMapping("/report/{id}")
    public ResponseEntity<?> getReportById(@PathVariable(value = "id") Long reportId) {
        return ResponseEntity.ok(reportDetailsService.findOneReportById(reportId));
    }

    @PostMapping("/report")
    public ResponseEntity<?> saveReport(@Valid @RequestBody ReportDto report) throws Exception {
        return ResponseEntity.ok(reportDetailsService.save(report));
    }

    @DeleteMapping("/report/{id}")
    public Map<String, Boolean> deleteReport(@PathVariable (value = "id") Long reportId)
            throws ResourceNotFoundException {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new ResourceNotFoundException("Report not found for this id" + reportId));

        reportRepository.delete(report);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Report was successful delete", Boolean.TRUE);
        return response;
    }

    @PutMapping("/report/{id}")
    public ResponseEntity<?> updateReport(@PathVariable(value = "id") Long reportId,
                                          @RequestBody ReportDto reportDto) throws ResourceNotFoundException {
        return ResponseEntity.ok(reportDetailsService.updateReport(reportId, reportDto));
    }

    @GetMapping("/report/userReport/{id}")
    public ResponseEntity<?> getUserReport(@PathVariable("id") Long userId) {
        return ResponseEntity.ok(reportDetailsService.findAllReportBy(userId));
    }


}


