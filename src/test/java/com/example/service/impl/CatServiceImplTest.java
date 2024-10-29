package com.example.service.impl;

import com.example.dto.BreedDTO;
import com.example.dto.CatDTO;
import com.example.dto.ColorDTO;
import com.example.entity.Breed;
import com.example.entity.Cat;
import com.example.entity.Color;
import com.example.enums.AnimalType;
import com.example.enums.Gender;
import com.example.exception.EntityNotFoundException;
import com.example.mapper.BreedMapper;
import com.example.mapper.BreedMapperImpl;
import com.example.mapper.CatMapper;
import com.example.mapper.CatMapperImpl;
import com.example.repository.BreedRepository;
import com.example.repository.CatRepository;
import com.example.service.BreedService;
import com.example.service.CatService;
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
@ContextConfiguration(classes = CatMapperImpl.class)
class CatServiceImplTest {
    private CatService catService;
    @Mock
    private CatRepository catRepository;
    @Autowired
    private CatMapper catMapper;
    private CatDTO catDTO;
    private Cat cat;

    @BeforeEach
    void setup() {
        catService = new CatServiceImpl(catRepository, catMapper);

        catDTO = new CatDTO(1L, "some name", LocalDate.of(2024,10, 10),
                new BreedDTO(1L, "some breed", "CAT"), new ColorDTO(1L, "some color"),
                "MALE", "some info");

        cat = new Cat(1L, "some name", LocalDate.of(2024,10, 10),
                new Breed(1L, "some breed", AnimalType.CAT), new Color(1L, "some color"),
                Gender.MALE, "some info");
    }

    @Test
    void testGetCatById_Success() {
        when(catRepository.findById(anyLong())).thenReturn(Optional.of(cat));
        CatDTO actualCatDTO = catService.getCatById(1L);
        assertEquals(catDTO, actualCatDTO);
    }

    @Test
    void testGetCatById_EntityNotFound() {
        when(catRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> catService.getCatById(1L));
    }

    @Test
    void testAddCat_Success() {
        when(catRepository.save(any())).thenReturn(cat);
        CatDTO actualCatDTO = catService.addCat(catDTO);
        assertEquals(catDTO, actualCatDTO);
        verify(catRepository, times(1)).save(any());
    }

    @Test
    void testUpdateCat_Success() {
        when(catRepository.findById(anyLong())).thenReturn(Optional.of(cat));
        when(catRepository.save(any())).thenReturn(cat);
        CatDTO actualCatDTO = catService.updateCat(1L, catDTO);
        assertEquals(catDTO, actualCatDTO);
        verify(catRepository, times(1)).save(any());
    }

    @Test
    void testUpdateCat_EntityNotFound() {
        when(catRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> catService.updateCat(1L, catDTO));
    }

    @Test
    void testDeleteCat_Success() {
        catService.deleteCatById(1L);
        verify(catRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetAllCats_Success() {
        Pageable pageable = PageRequest.of(1, 1);
        when(catRepository.findAll((Pageable) any())).thenReturn(
                new PageImpl<Cat>(List.of(cat), pageable, 1)
        );
        Page<CatDTO> expectedPage = new PageImpl<>(List.of(catDTO), pageable, 1);
        Page<CatDTO> actualPate = catService.getAllCats(pageable);
        assertEquals(expectedPage, actualPate);
    }
}