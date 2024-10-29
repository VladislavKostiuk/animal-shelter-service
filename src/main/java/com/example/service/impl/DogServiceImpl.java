package com.example.service.impl;

import com.example.dto.DogDTO;
import com.example.entity.Dog;
import com.example.exception.EntityNotFoundException;
import com.example.mapper.DogMapper;
import com.example.repository.DogRepository;
import com.example.service.DogService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class DogServiceImpl implements DogService {
    private final DogRepository dogRepository;
    private final DogMapper dogMapper;

    @Override
    public DogDTO getDogById(Long id) {
        Dog dog = dogRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Dog", id));
        return dogMapper.dogToDogDTO(dog);
    }

    @Override
    public DogDTO addDog(DogDTO dogDTO) {
        Dog dog = dogMapper.dogDTOtoDog(dogDTO);
        dog.setId(0L);
        return dogMapper.dogToDogDTO(dogRepository.save(dog));
    }

    @Override
    public DogDTO updateDog(Long id, DogDTO dogDTO) {
        if (dogRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Breed", id);
        }

        Dog dog = dogMapper.dogDTOtoDog(dogDTO);
        dog.setId(id);
        return dogMapper.dogToDogDTO(dogRepository.save(dog));
    }

    @Override
    public void deleteDogById(Long id) {
        dogRepository.deleteById(id);
    }

    @Override
    public Page<DogDTO> getAllDogs(Pageable pageable) {
        return dogRepository.findAll(pageable).map(dogMapper::dogToDogDTO);
    }
}
