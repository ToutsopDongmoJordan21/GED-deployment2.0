package com.example.caref.controllers;

import com.example.caref.execption.ResourceNotFoundException;
import com.example.caref.models.Garage;
import com.example.caref.models.dto.GarageDto;
import com.example.caref.repository.GarageRepository;
import com.example.caref.service.GarageDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class GarageController {
    @Autowired
    private GarageRepository garageRepository;

    @Autowired
    private GarageDetailsService garageDetailsService;

    @GetMapping("/garage")
    public ResponseEntity<?> getAllAllGarage() {

        return ResponseEntity.ok(garageDetailsService.findAllGarage());
    }

    @GetMapping("/garage/{id}")
    public ResponseEntity<?> getGarageById(@PathVariable(value = "id") Long garageId) {
        return ResponseEntity.ok(garageDetailsService.findOneGarageById(garageId));
    }

    @PostMapping("/garage")
    public ResponseEntity<?> saveGarage(@Valid @RequestBody GarageDto garage) throws Exception {
        return ResponseEntity.ok(garageDetailsService.save(garage));
    }

    @DeleteMapping("/garage/{id}")
    public Map<String, Boolean> deleteGarage(@PathVariable(value = "id") Long garageId)
            throws ResourceNotFoundException {
        Garage garage = garageRepository.findById(garageId)
                .orElseThrow(() -> new ResourceNotFoundException("garage not found for this id :: " +garageId));

        garageRepository.delete(garage);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Garage was successful delete", Boolean.TRUE);
        return response;
    }

    @PutMapping("/garage/{id}")
    public ResponseEntity<?> updateGarage(@PathVariable(value = "id") Long garageId,
                                          @RequestBody GarageDto garageDto) throws ResourceNotFoundException {

        return ResponseEntity.ok(garageDetailsService.updateGarage(garageId, garageDto));
    }

}

