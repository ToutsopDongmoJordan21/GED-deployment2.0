package com.example.caref.repository;

import com.example.caref.models.Accessoire;
import com.example.caref.models.Car;
import com.example.caref.models.CarAccessoire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarAccessoireRepository extends JpaRepository<CarAccessoire, Long> {
    List<CarAccessoire> findByCar(Car car);
    CarAccessoire findByCarAndAccessoire(Car car, Accessoire accessoire);
}
