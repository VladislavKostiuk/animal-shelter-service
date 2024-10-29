package com.example.service;

import com.example.dto.DogDTO;
import com.example.entity.Dog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DogService {
    DogDTO getDogById(Long id);
    DogDTO addDog(DogDTO dogDTO);
    DogDTO updateDog(Long id, DogDTO dogDTO);
    void deleteDogById(Long id);
    Page<DogDTO> getAllDogs(Pageable pageable);
}
