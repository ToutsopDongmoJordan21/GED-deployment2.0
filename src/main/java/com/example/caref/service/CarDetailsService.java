package com.example.caref.service;

import com.example.caref.execption.ResourceNotFoundException;
import com.example.caref.files.dto.FileDto;
import com.example.caref.files.services.FileService;
import com.example.caref.models.Accessoire;
import com.example.caref.models.Car;
import com.example.caref.models.CarAccessoire;
import com.example.caref.models.User;
import com.example.caref.models.dto.CarDto;
import com.example.caref.models.dto.CarResponseDto;
import com.example.caref.repository.AccessoireRepository;
import com.example.caref.repository.CarAccessoireRepository;
import com.example.caref.repository.CarRepository;
import com.example.caref.repository.UserRepository;
import com.example.caref.security.util.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarDetailsService {

    private final CarRepository carRepository;
    private final CarAccessoireRepository carAccessoireRepository;
    private final AccessoireRepository accesoireRepository;
    private final FileService fileService;
    private final UserRepository userRepository;

    public CarResponseDto save(CarDto car){
        User user = userRepository.getOne(SecurityUtils.getCurrentUserId());
        Car newCar = new Car();
        newCar.setCarTitle(car.getCarTitle());
        newCar.setCarBrand(car.getCarBrand());
        newCar.setCarOverview(car.getCarOverview());
        newCar.setCarLoanPrice(car.getCarLoanPrice());
        newCar.setCarFuel(car.getCarFuel());
        newCar.setCarYearModel(car.getCarYearModel());
        newCar.setCarSeating(car.getCarSeating());
        newCar.setCarPrice(car.getCarPrice());
        newCar.setCarAddedDate(new Date());
        newCar.setUser(user);
        //save new car
        newCar = carRepository.save(newCar);

        if(!car.getAccessors().isEmpty()) {
            Car finalNewCar = newCar;
            car.getAccessors().forEach(accId -> {
                Accessoire accessoire = accesoireRepository.getOne(accId);
                CarAccessoire carAccessoire = new CarAccessoire();
                carAccessoire.setAccessoire(accessoire);
                carAccessoire.setCar(finalNewCar);
                carAccessoireRepository.save(carAccessoire);
            });
        }
        return findOneCarById(newCar.getId());
    }


    public CarResponseDto findOneCarById(Long carId) {
        Car car = carRepository.getOne(carId);
        Optional<User> optionalUser = userRepository.findByUsername(car.getCreatedBy());
        List<CarAccessoire> accessors = carAccessoireRepository.findByCar(car);
        List<FileDto> files = fileService.findCarFile(carId);
        return CarResponseDto.CarResponseDtoBuilder.aCarResponseDto()
                .withId(carId)
                .withCarTitle(car.getCarTitle())
                .withCarFuel(car.getCarFuel())
                .withCarBrand(car.getCarBrand())
                .withCarAddedDate(car.getCarAddedDate(new Date()))
                .withCarLoanPrice(car.getCarLoanPrice())
                .withCarPrice(car.getCarPrice())
                .withCarOverview(car.getCarOverview())
                .withCarSeating(car.getCarSeating())
                .withCarYearModel(car.getCarYearModel())
                .withPostById(optionalUser.map(User::getId).orElse(null))
                .withPostByName(optionalUser.map(User::getUsername).orElse(null))
                .withAccessorsId(accessors.isEmpty() ? null : accessors.stream().map(CarAccessoire::getAccessoire).map(Accessoire::getId).collect(Collectors.toList()))
                .withAccessorsName(accessors.isEmpty() ? null : accessors.stream().map(CarAccessoire::getAccessoire).map(Accessoire::getAccessoireName).collect(Collectors.toList()))
                .withCarImages(files)
                .build();
    }

    public CarResponseDto updateCar(Long carId, CarDto dto) throws ResourceNotFoundException {
        Car car = carRepository.findById(carId).orElseThrow(() -> new ResourceNotFoundException("Car not found for this :: " +carId));
        List<CarAccessoire> accessors = carAccessoireRepository.findByCar(car);

        if(Objects.nonNull(dto.getCarTitle())) {
            car.setCarTitle(dto.getCarTitle());
        }
        if(Objects.nonNull(dto.getCarBrand())) {
            car.setCarBrand(dto.getCarBrand());
        }
        if(Objects.nonNull(dto.getCarOverview())) {
            car.setCarOverview(dto.getCarOverview());
        }
        if(Objects.nonNull(dto.getCarLoanPrice())) {
            car.setCarLoanPrice(dto.getCarLoanPrice());
        }
        if(Objects.nonNull(dto.getCarFuel())) {
            car.setCarFuel(dto.getCarFuel());
        }
        if(Objects.nonNull(dto.getCarYearModel())) {
            car.setCarYearModel(dto.getCarYearModel());
        }
        if(Objects.nonNull(dto.getCarSeating())) {
            car.setCarSeating(dto.getCarSeating());
        }
        if(Objects.nonNull(dto.getCarPrice())) {
            car.setCarPrice(dto.getCarPrice());
        }

        if(!dto.getAccessors().isEmpty()) {
            if(!accessors.isEmpty()) {
                accessors.forEach(carAccessoire1 ->
                        carAccessoireRepository.delete(carAccessoire1));
            }


            Car finalNewCar = car;

            dto.getAccessors().forEach(accId -> {
                Accessoire accessoire = accesoireRepository.getOne(accId);
                CarAccessoire carAccessoires = new CarAccessoire();
                carAccessoires.setAccessoire(accessoire);
                carAccessoires.setCar(finalNewCar);
                carAccessoireRepository.save(carAccessoires);
            });
        }
        car = carRepository.save(car);
        return findOneCarById(car.getId());

    }

    public List<CarResponseDto> findAllCar() {
        return carRepository.findAll().stream().map(buildListCarResponseDto()).collect(Collectors.toList());
    }


    public List<CarResponseDto> findAllByCreatedBy(Long userId) {
        return carRepository.findAllByCreatedBy(userId).stream().map(buildListCarResponseDto()).collect(Collectors.toList());
    }

    private Function<Car, CarResponseDto> buildListCarResponseDto() {
        return car -> findOneCarById(car.getId());
    }

}

