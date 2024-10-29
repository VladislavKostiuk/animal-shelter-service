package com.example.service;

import com.example.dto.CatDTO;
import com.example.dto.ColorDTO;
import com.example.entity.Color;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ColorService {
    ColorDTO getColorById(Long id);
    ColorDTO addColor(ColorDTO colorDTO);
    ColorDTO updateColor(Long id, ColorDTO colorDTO);
    void deleteColorById(Long id);
    Page<ColorDTO> getAllColors(Pageable pageable);
}
