package com.example.caref.service;

import com.example.caref.execption.ResourceNotFoundException;
import com.example.caref.models.Accessoire;
import com.example.caref.models.dto.AccessoireDto;
import com.example.caref.models.dto.AccessoireResponseDto;
import com.example.caref.repository.AccessoireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class AccessoireDetailsService {

    @Autowired
    private AccessoireRepository accessoireRepository;

    public AccessoireResponseDto save(AccessoireDto accessoire) {
        Accessoire newAccessoire = new Accessoire();
        newAccessoire.setAccessoireName(accessoire.getAccessoireName());

        newAccessoire = accessoireRepository.save(newAccessoire);
        return findOneAccessoireById(newAccessoire.getId());
    }

    public AccessoireResponseDto findOneAccessoireById(Long accessoireId) {
        Accessoire accessoire = accessoireRepository.getOne(accessoireId);
        return AccessoireResponseDto.AccessoireResponseDtoBuilder.aAccessoireResponseDto()
                .withId(accessoireId)
                .withAccessoireName(accessoire.getAccessoireName())
                .build();
    }

    public AccessoireResponseDto updateAccessoire(Long accessoireId, AccessoireDto accessoireDto) throws ResourceNotFoundException {
        Accessoire accessoire = accessoireRepository.findById(accessoireId).orElseThrow(() -> new ResourceNotFoundException("Accessoire not found for this id ::" +accessoireId));

        if(Objects.nonNull(accessoireDto.getAccessoireName())) {
            accessoire.setAccessoireName(accessoireDto.getAccessoireName());
        }
        Accessoire finalNewAccessoire = accessoire;
        accessoire = accessoireRepository.save(accessoire);
        return findOneAccessoireById(accessoire.getId());
    }

    public List<AccessoireResponseDto> findAllAccessoire() {
        return accessoireRepository.findAll().stream().map(buildListAccessoireResponseDto()).collect(Collectors.toList());
    }

    private Function<Accessoire, AccessoireResponseDto> buildListAccessoireResponseDto() {
        return accessoire -> findOneAccessoireById(accessoire.getId());
    }
}

