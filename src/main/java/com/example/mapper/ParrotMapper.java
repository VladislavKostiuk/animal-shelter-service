package com.example.mapper;

import com.example.dto.ParrotDTO;
import com.example.entity.Parrot;
import com.example.enums.Gender;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ParrotMapper extends GenderMapper{
    @Mapping(source = "gender", target = "gender", qualifiedByName = "genderName")
    ParrotDTO parrotToParrotDTO(Parrot parrot);

    @Mapping(source = "gender", target = "gender", qualifiedByName = "gender")
    Parrot parrotDTOtoParrot(ParrotDTO parrotDTO);
}
