package com.example.service.impl;

import com.example.dto.BreedDTO;
import com.example.dto.ColorDTO;
import com.example.dto.DogDTO;
import com.example.entity.Breed;
import com.example.entity.Color;
import com.example.entity.Dog;
import com.example.enums.AnimalType;
import com.example.enums.Gender;
import com.example.exception.EntityNotFoundException;
import com.example.mapper.DogMapper;
import com.example.mapper.DogMapperImpl;
import com.example.repository.DogRepository;
import com.example.service.DogService;
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

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DogMapperImpl.class)
class DogServiceImplTest {
    private DogService dogService;
    @Mock
    private DogRepository dogRepository;
    @Autowired
    private DogMapper dogMapper;
    private DogDTO dogDTO;
    private Dog dog;

    @BeforeEach
    void setup() {
        dogService = new DogServiceImpl(dogRepository, dogMapper);

        dogDTO = new DogDTO(1L, "some name", LocalDate.of(2024,10, 10),
                new BreedDTO(1L, "some breed", "DOG"), new ColorDTO(1L, "some color"),
                "MALE", "some info");

        dog = new Dog(1L, "some name", LocalDate.of(2024,10, 10),
                new Breed(1L, "some breed", AnimalType.DOG), new Color(1L, "some color"),
                Gender.MALE, "some info");
    }

    @Test
    void testGetDogById_Success() {
        when(dogRepository.findById(anyLong())).thenReturn(Optional.of(dog));
        DogDTO actualDogDTO = dogService.getDogById(1L);
        assertEquals(dogDTO, actualDogDTO);
    }

    @Test
    void testGetDogById_EntityNotFound() {
        when(dogRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> dogService.getDogById(1L));
    }

    @Test
    void testAddDog_Success() {
        when(dogRepository.save(any())).thenReturn(dog);
        DogDTO actualDogDTO = dogService.addDog(dogDTO);
        assertEquals(dogDTO, actualDogDTO);
        verify(dogRepository, times(1)).save(any());
    }

    @Test
    void testUpdateDog_Success() {
        when(dogRepository.findById(anyLong())).thenReturn(Optional.of(dog));
        when(dogRepository.save(any())).thenReturn(dog);
        DogDTO actualDogDTO = dogService.updateDog(1L, dogDTO);
        assertEquals(dogDTO, actualDogDTO);
        verify(dogRepository, times(1)).save(any());
    }

    @Test
    void testUpdateDog_EntityNotFound() {
        when(dogRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> dogService.updateDog(1L, dogDTO));
    }

    @Test
    void testDeleteDog_Success() {
        dogService.deleteDogById(1L);
        verify(dogRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetAllDogs_Success() {
        Pageable pageable = PageRequest.of(1, 1);
        when(dogRepository.findAll((Pageable) any())).thenReturn(
                new PageImpl<>(List.of(dog), pageable, 1)
        );
        Page<DogDTO> expectedPage = new PageImpl<>(List.of(dogDTO), pageable, 1);
        Page<DogDTO> actualPate = dogService.getAllDogs(pageable);
        assertEquals(expectedPage, actualPate);
    }
}