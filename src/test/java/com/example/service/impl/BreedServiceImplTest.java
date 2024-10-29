package com.example.service.impl;

import com.example.dto.BreedDTO;
import com.example.entity.Breed;
import com.example.enums.AnimalType;
import com.example.exception.EntityNotFoundException;
import com.example.mapper.BreedMapper;
import com.example.mapper.BreedMapperImpl;
import com.example.repository.BreedRepository;
import com.example.service.BreedService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = BreedMapperImpl.class)
class BreedServiceImplTest {
    private BreedService breedService;
    @Mock
    private BreedRepository breedRepository;
    @Autowired
    private BreedMapper breedMapper;
    private BreedDTO breedDTO;
    private Breed breed;

    @BeforeEach
    void setup() {
        breedService = new BreedServiceImpl(breedRepository, breedMapper);
        breedDTO = new BreedDTO(1L, "some name", "CAT");
        breed = new Breed(1L,"some name", AnimalType.CAT);
    }

    @Test
    void testGetBreedById_Success() {
        when(breedRepository.findById(anyLong())).thenReturn(Optional.of(breed));
        BreedDTO actualBreedDTO = breedService.getBreedById(1L);
        assertEquals(breedDTO, actualBreedDTO);
    }

    @Test
    void testGetBreedById_EntityNotFound() {
        when(breedRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> breedService.getBreedById(1L));
    }

    @Test
    void testAddBreed_Success() {
        when(breedRepository.save(any())).thenReturn(breed);
        BreedDTO actualBreedDTO = breedService.addBreed(breedDTO);
        assertEquals(breedDTO, actualBreedDTO);
        verify(breedRepository, times(1)).save(any());
    }

    @Test
    void testUpdateBreed_Success() {
        when(breedRepository.findById(anyLong())).thenReturn(Optional.of(breed));
        when(breedRepository.save(any())).thenReturn(breed);
        BreedDTO actualBreedDTO = breedService.updateBreed(1L, breedDTO);
        assertEquals(breedDTO, actualBreedDTO);
        verify(breedRepository, times(1)).save(any());
    }

    @Test
    void testUpdateBreed_EntityNotFound() {
        when(breedRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> breedService.updateBreed(1L, breedDTO));
    }

    @Test
    void testDeleteBreed_Success() {
        breedService.deleteBreedById(1L);
        verify(breedRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetAllBreeds_Success() {
        Pageable pageable = PageRequest.of(1, 1);
        when(breedRepository.findAll((Pageable) any())).thenReturn(
                new PageImpl<>(List.of(breed), pageable, 1)
        );
        Page<BreedDTO> expectedPage = new PageImpl<BreedDTO>(List.of(breedDTO), pageable, 1);
        Page<BreedDTO> actualPate = breedService.getAllBreeds(pageable);
        assertEquals(expectedPage, actualPate);
    }
}