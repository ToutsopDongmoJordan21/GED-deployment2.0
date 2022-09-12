package com.example.caref.repository;

import com.example.caref.models.Accessoire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccessoireRepository extends JpaRepository<Accessoire, Long> {

    public Accessoire findByAccessoireName(String accessoireName);

    public Accessoire findById(long id);

    public List<Accessoire> findAll();

    public long count();
}
