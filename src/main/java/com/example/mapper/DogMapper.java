package com.example.mapper;

import com.example.dto.DogDTO;
import com.example.entity.Dog;
import com.example.enums.Gender;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface DogMapper extends GenderMapper{
    @Mapping(source = "gender", target = "gender", qualifiedByName = "genderName")
    DogDTO dogToDogDTO(Dog dog);

    @Mapping(source = "gender", target = "gender", qualifiedByName = "gender")
    Dog dogDTOtoDog(DogDTO dogDTO);
}
