package com.example.caref.repository;

import com.example.caref.models.Report;
import com.example.caref.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    public Report findById(long id);

    public List<Report> findAll();

    public List<Report> findAllByUser(User user);

}