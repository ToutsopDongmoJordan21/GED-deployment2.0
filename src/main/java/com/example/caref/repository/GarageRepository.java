package com.example.caref.repository;

import com.example.caref.models.Garage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GarageRepository extends JpaRepository<Garage, Long> {

    public Garage findByGarageName(String garageName);

    public Garage findById(long id);

    public List<Garage> findAll();

    public long count();
}
