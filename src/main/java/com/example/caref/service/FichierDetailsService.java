package com.example.caref.service;

import com.example.caref.files.dto.FileDto;
import com.example.caref.files.services.FileService;
import com.example.caref.models.Fichier;
import com.example.caref.models.User;
import com.example.caref.models.dto.FichierDto;
import com.example.caref.models.dto.FichierResponseDto;
import com.example.caref.repository.FichierRepository;
import com.example.caref.repository.UserRepository;
import com.example.caref.security.util.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FichierDetailsService {

    private final FichierRepository fichierRepository;
    private final FileService fileService;
    private final UserRepository userRepository;

    public FichierResponseDto save(FichierDto fichier) {
        User user = userRepository.getOne(SecurityUtils.getCurrentUserId());
        Fichier newFichier = new Fichier();
        newFichier.setFichierTitle(fichier.getFichierTitle());
        newFichier.setFichierAddedDate(new Date());
        newFichier.setUser(user);

        newFichier = fichierRepository.save(newFichier);

        return findOneFichierById(newFichier.getId());
    }

    public FichierResponseDto findOneFichierById(Long fichierId) {
        Fichier fichier = fichierRepository.getOne(fichierId);
        Optional<User> optionalUser = userRepository.findByUsername(fichier.getCreatedBy());
        List<FileDto> files = fileService.findFichierFile(fichierId);


        return FichierResponseDto.FichierResponseDtoBuilder.aFichierResponseDto()
                .withId(fichierId)
                .withFichierTitle(fichier.getFichierTitle())
                .withFichierAddedDate(fichier.getFichierAddedDate(new Date()))
                .withPostById(optionalUser.map(User::getId).orElse(null))
                .withPostByName(optionalUser.map(User::getUsername).orElse(null))

                .withFichierImage(files)
                .build();

    }

    public List<FichierResponseDto> findAllFichier() {
        return fichierRepository.findAll().stream().map(buildListCarResponseDto()).collect(Collectors.toList());
    }

    public List<FichierResponseDto> findAllCreatedBy(Long userId) {
        return fichierRepository.findAllByCreatedBy(userId).stream().map(buildListCarResponseDto()).collect(Collectors.toList());
    }

    private Function<Fichier, FichierResponseDto>  buildListCarResponseDto() {
        return fichier -> findOneFichierById(fichier.getId());
    }







}
