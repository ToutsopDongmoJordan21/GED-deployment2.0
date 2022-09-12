package com.example.caref.controllers;


import com.example.caref.execption.ResourceNotFoundException;
import com.example.caref.models.Fichier;
import com.example.caref.models.dto.FichierDto;
import com.example.caref.repository.FichierRepository;
import com.example.caref.service.FichierDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class FichierController {

    @Autowired
    private FichierRepository fichierRepository;

    @Autowired
    private FichierDetailsService fichierDetailsService;

    @GetMapping("/fichiers")
    public ResponseEntity<?> getAllFichier() {
        return ResponseEntity.ok(fichierDetailsService.findAllFichier());
    }

    @GetMapping("/fichiers/userCreated/{id}")
    public ResponseEntity<?> getUserCreated(@PathVariable ("id") Long userId) {
        return ResponseEntity.ok(fichierDetailsService.findAllCreatedBy(userId));
    }

    @DeleteMapping("/fichiers/{id}")
    public Map<String, Boolean> deleteFichier(@PathVariable(value = "id") Long fichierId)
            throws ResourceNotFoundException {
        Fichier fichier = fichierRepository.findById(fichierId)
                .orElseThrow(() -> new ResourceNotFoundException("Fichier not found for this id :: " +fichierId));

        fichierRepository.delete(fichier);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Fichier was successful delete", Boolean.TRUE);
        return response;
    }

    @PostMapping(value = "/fichiers")
    public ResponseEntity<?> saveFichier(@RequestBody FichierDto fichier) throws Exception {
        return ResponseEntity.ok(fichierDetailsService.save(fichier));
    }

    @GetMapping("/fichiers/{id}")
    public ResponseEntity<?> getFichierById(@PathVariable(value = "id") Long fichierId) {
        return ResponseEntity.ok(fichierDetailsService.findOneFichierById(fichierId));
    }
}
