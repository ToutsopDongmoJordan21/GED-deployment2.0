package com.example.caref.service;

import com.example.caref.execption.ResourceNotFoundException;
import com.example.caref.files.dto.FileDto;
import com.example.caref.files.services.FileService;
import com.example.caref.models.Garage;
import com.example.caref.models.dto.GarageDto;
import com.example.caref.models.dto.GarageResponseDto;
import com.example.caref.repository.GarageRepository;
import com.example.caref.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class GarageDetailsService {

    @Autowired
    private GarageRepository garageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FileService fileService;

    public GarageResponseDto save(GarageDto garage) {
        Garage newGarage = new Garage();
        newGarage.setGarageAddress(garage.getGarageAddress());
        newGarage.setGarageName(garage.getGarageName());
        newGarage = garageRepository.save(newGarage);

        return findOneGarageById(newGarage.getId());

    }

    public GarageResponseDto findOneGarageById(Long garageId) {
        Garage garage = garageRepository.getOne(garageId);
        List<FileDto> files = fileService.findGarageFile(garageId);
        return GarageResponseDto.GarageResponseDtoBuilder.aGarageResponseDto()
                .withId(garageId)
                .withGarageName(garage.getGarageName())
                .withGarageAddress(garage.getGarageAddress())
                .withGarageImages(files)
                .build();
    }

    public GarageResponseDto updateGarage(Long garageId, GarageDto dto) throws ResourceNotFoundException {
        Garage garage = garageRepository.findById(garageId).orElseThrow(() -> new ResourceNotFoundException("Garage not found for this id ::" +garageId));

        if(Objects.nonNull(dto.getGarageName())) {
            garage.setGarageName(dto.getGarageName());
        }
        if(Objects.nonNull(dto.getGarageAddress())) {
            garage.setGarageAddress(dto.getGarageAddress());
        }

        Garage finalNewGarage = garage;

        garage = garageRepository.save(garage);
        return findOneGarageById(garage.getId());
    }

    public List<GarageResponseDto> findAllGarage() {
        return garageRepository.findAll().stream().map(buildListGarageResponseDto()).collect(Collectors.toList());
    }

    private Function<Garage, GarageResponseDto> buildListGarageResponseDto() {
        return garage -> findOneGarageById(garage.getId());
    }
}

