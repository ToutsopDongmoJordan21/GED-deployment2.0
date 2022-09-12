package com.example.caref.repository;

import com.example.caref.models.Car;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query(value = "SELECT * FROM cars where user_id=?", nativeQuery = true)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public List<Car> findAllByCreatedBy(Long userId);

    public Car findByCarTitle(String carTitle);

    public Car findById(long id);

    public List<Car> findAll();

    public long count();

}
