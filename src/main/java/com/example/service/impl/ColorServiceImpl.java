package com.example.service.impl;

import com.example.dto.ColorDTO;
import com.example.entity.Breed;
import com.example.entity.Color;
import com.example.exception.EntityNotFoundException;
import com.example.mapper.ColorMapper;
import com.example.repository.ColorRepository;
import com.example.service.ColorService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {
    private final ColorRepository colorRepository;
    private final ColorMapper colorMapper;

    @Override
    public ColorDTO getColorById(Long id) {
        Color color = colorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Color", id));
        return colorMapper.colorToColorDTO(color);
    }

    @Override
    public ColorDTO addColor(ColorDTO colorDTO) {
        Color color = colorMapper.colorDTOtoColor(colorDTO);
        color.setId(0L);
        return colorMapper.colorToColorDTO(colorRepository.save(color));
    }

    @Override
    public ColorDTO updateColor(Long id, ColorDTO colorDTO) {
        if (colorRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Color", id);
        }

        Color color = colorMapper.colorDTOtoColor(colorDTO);
        color.setId(id);
        return colorMapper.colorToColorDTO(colorRepository.save(color));
    }

    @Override
    public void deleteColorById(Long id) {
        colorRepository.deleteById(id);
    }

    @Override
    public Page<ColorDTO> getAllColors(Pageable pageable) {
        return colorRepository.findAll(pageable).map(colorMapper::colorToColorDTO);
    }
}
