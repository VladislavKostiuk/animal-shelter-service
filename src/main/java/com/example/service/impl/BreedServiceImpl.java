package com.example.service.impl;

import com.example.dto.BreedDTO;
import com.example.entity.Breed;
import com.example.exception.EntityNotFoundException;
import com.example.mapper.BreedMapper;
import com.example.repository.BreedRepository;
import com.example.service.BreedService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class BreedServiceImpl implements BreedService {
    private final BreedRepository breedRepository;
    private final BreedMapper breedMapper;

    @Override
    public BreedDTO getBreedById(Long id) {
        Breed breed = breedRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Breed", id));
        return breedMapper.breedToBreedDTO(breed);
    }

    @Override
    public BreedDTO addBreed(BreedDTO breedDTO) {
        Breed breed = breedMapper.breedDTOtoBreed(breedDTO);
        breed.setId(0L);
        return breedMapper.breedToBreedDTO(breedRepository.save(breed));
    }

    @Override
    public BreedDTO updateBreed(Long id, BreedDTO breedDTO) {
        if (breedRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Breed", id);
        }

        Breed breed = breedMapper.breedDTOtoBreed(breedDTO);
        breed.setId(id);
        return breedMapper.breedToBreedDTO(breedRepository.save(breed));
    }

    @Override
    public void deleteBreedById(Long id) {
        breedRepository.deleteById(id);
    }

    @Override
    public Page<BreedDTO> getAllBreeds(Pageable pageable) {
        return breedRepository.findAll(pageable).map(breedMapper::breedToBreedDTO);
    }
}
