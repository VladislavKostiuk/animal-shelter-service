package com.example.mapper;

import com.example.dto.ColorDTO;
import com.example.entity.Color;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ColorMapper {
    ColorDTO colorToColorDTO(Color color);
    Color colorDTOtoColor(ColorDTO colorDTO);
}
