package com.example.service;

import com.example.dto.BreedDTO;
import com.example.entity.Breed;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BreedService {
    BreedDTO getBreedById(Long id);
    BreedDTO addBreed(BreedDTO breedDTO);
    BreedDTO updateBreed(Long id, BreedDTO breedDTO);
    void deleteBreedById(Long id);
    Page<BreedDTO> getAllBreeds(Pageable pageable);
}
