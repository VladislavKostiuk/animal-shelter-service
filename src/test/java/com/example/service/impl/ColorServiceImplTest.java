package com.example.service.impl;

import com.example.dto.ColorDTO;
import com.example.entity.Color;
import com.example.exception.EntityNotFoundException;
import com.example.mapper.ColorMapper;
import com.example.mapper.ColorMapperImpl;
import com.example.repository.ColorRepository;
import com.example.service.ColorService;
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
@ContextConfiguration(classes = ColorMapperImpl.class)
class ColorServiceImplTest {
    private ColorService colorService;
    @Mock
    private ColorRepository colorRepository;
    @Autowired
    private ColorMapper colorMapper;
    private ColorDTO colorDTO;
    private Color color;

    @BeforeEach
    void setup() {
        colorService = new ColorServiceImpl(colorRepository, colorMapper);
        colorDTO = new ColorDTO(1L, "some name");
        color = new Color(1L,"some name");
    }

    @Test
    void testGetColorById_Success() {
        when(colorRepository.findById(anyLong())).thenReturn(Optional.of(color));
        ColorDTO actualColorDTO = colorService.getColorById(1L);
        assertEquals(colorDTO, actualColorDTO);
    }

    @Test
    void testGetColorById_EntityNotFound() {
        when(colorRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> colorService.getColorById(1L));
    }

    @Test
    void testAddColor_Success() {
        when(colorRepository.save(any())).thenReturn(color);
        ColorDTO actualColorDTO = colorService.addColor(colorDTO);
        assertEquals(colorDTO, actualColorDTO);
        verify(colorRepository, times(1)).save(any());
    }

    @Test
    void testUpdateColor_Success() {
        when(colorRepository.findById(anyLong())).thenReturn(Optional.of(color));
        when(colorRepository.save(any())).thenReturn(color);
        ColorDTO actualColorDTO = colorService.updateColor(1L, colorDTO);
        assertEquals(colorDTO, actualColorDTO);
        verify(colorRepository, times(1)).save(any());
    }

    @Test
    void testUpdateColor_EntityNotFound() {
        when(colorRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> colorService.updateColor(1L, colorDTO));
    }

    @Test
    void testDeleteColor_Success() {
        colorService.deleteColorById(1L);
        verify(colorRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetAllColors_Success() {
        Pageable pageable = PageRequest.of(1, 1);
        when(colorRepository.findAll((Pageable) any())).thenReturn(
                new PageImpl<>(List.of(color), pageable, 1)
        );
        Page<ColorDTO> expectedPage = new PageImpl<>(List.of(colorDTO), pageable, 1);
        Page<ColorDTO> actualPate = colorService.getAllColors(pageable);
        assertEquals(expectedPage, actualPate);
    }
}