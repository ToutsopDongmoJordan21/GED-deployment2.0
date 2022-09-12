package com.example.caref.files.repositories;

import com.example.caref.files.entities.CarefFile;
import com.example.caref.files.entities.enumeration.DocType;
import com.example.caref.models.Car;
import com.example.caref.models.Garage;
import com.example.caref.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<CarefFile, Long> {

    List<CarefFile> findByCar(Car car);

    List<CarefFile> findByGarage(Garage garage);

    CarefFile findByUserAndType(User user, DocType type);

    CarefFile findByFileName(String fileName);
}
