package com.example.mapper;

import com.example.dto.CatDTO;
import com.example.entity.Cat;
import com.example.entity.Color;
import com.example.enums.Gender;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CatMapper extends GenderMapper{

    @Mapping(source = "gender", target = "gender", qualifiedByName = "genderName")
    CatDTO catToCatDTO(Cat cat);

    @Mapping(source = "gender", target = "gender")
    Cat catDTOtoCat(CatDTO catDTO);
}
