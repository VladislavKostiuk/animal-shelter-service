package com.example.service.impl;

import com.example.dto.BreedDTO;
import com.example.dto.ColorDTO;
import com.example.dto.ParrotDTO;
import com.example.entity.Breed;
import com.example.entity.Color;
import com.example.entity.Parrot;
import com.example.enums.AnimalType;
import com.example.enums.Gender;
import com.example.exception.EntityNotFoundException;
import com.example.mapper.ParrotMapper;
import com.example.mapper.ParrotMapperImpl;
import com.example.repository.ParrotRepository;
import com.example.service.ParrotService;
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
@ContextConfiguration(classes = ParrotMapperImpl.class)
class ParrotServiceImplTest {
    private ParrotService parrotService;
    @Mock
    private ParrotRepository parrotRepository;
    @Autowired
    private ParrotMapper parrotMapper;
    private ParrotDTO parrotDTO;
    private Parrot parrot;

    @BeforeEach
    void setup() {
        parrotService = new ParrotServiceImpl(parrotRepository, parrotMapper);

        parrotDTO = new ParrotDTO(1L, "some name", LocalDate.of(2024,10, 10),
                new BreedDTO(1L, "some breed", "PARROT"), new ColorDTO(1L, "some color"),
                "MALE", "some info");

        parrot = new Parrot(1L, "some name", LocalDate.of(2024,10, 10),
                new Breed(1L, "some breed", AnimalType.PARROT), new Color(1L, "some color"),
                Gender.MALE, "some info");
    }

    @Test
    void testGetParrotById_Success() {
        when(parrotRepository.findById(anyLong())).thenReturn(Optional.of(parrot));
        ParrotDTO actualParrotDTO = parrotService.getParrotById(1L);
        assertEquals(parrotDTO, actualParrotDTO);
    }

    @Test
    void testGetParrotById_EntityNotFound() {
        when(parrotRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> parrotService.getParrotById(1L));
    }

    @Test
    void testAddParrot_Success() {
        when(parrotRepository.save(any())).thenReturn(parrot);
        ParrotDTO actualParrotDTO = parrotService.addParrot(parrotDTO);
        assertEquals(parrotDTO, actualParrotDTO);
        verify(parrotRepository, times(1)).save(any());
    }

    @Test
    void testUpdateParrot_Success() {
        when(parrotRepository.findById(anyLong())).thenReturn(Optional.of(parrot));
        when(parrotRepository.save(any())).thenReturn(parrot);
        ParrotDTO actualParrotDTO = parrotService.updateParrot(1L, parrotDTO);
        assertEquals(parrotDTO, actualParrotDTO);
        verify(parrotRepository, times(1)).save(any());
    }

    @Test
    void testUpdateParrot_EntityNotFound() {
        when(parrotRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> parrotService.updateParrot(1L, parrotDTO));
    }

    @Test
    void testDeleteParrot_Success() {
        parrotService.deleteParrotById(1L);
        verify(parrotRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetAllParrots_Success() {
        Pageable pageable = PageRequest.of(1, 1);
        when(parrotRepository.findAll((Pageable) any())).thenReturn(
                new PageImpl<>(List.of(parrot), pageable, 1)
        );
        Page<ParrotDTO> expectedPage = new PageImpl<>(List.of(parrotDTO), pageable, 1);
        Page<ParrotDTO> actualPate = parrotService.getAllParrots(pageable);
        assertEquals(expectedPage, actualPate);
    }
}