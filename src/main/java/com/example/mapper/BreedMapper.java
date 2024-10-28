package com.example.mapper;

import com.example.dto.BreedDTO;
import com.example.entity.Breed;
import com.example.enums.AnimalType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface BreedMapper {
    @Mapping(source = "animal", target = "animal", qualifiedByName = "animalName")
    BreedDTO breedToBreedDTO(Breed breed);
    @Mapping(source = "animal", target = "animal", qualifiedByName = "animalType")
    Breed breedDTOtoBreed(BreedDTO breedDTO);

    @Named("animalName")
    default String AnimalTypeToAnimalName(AnimalType animalType) {
        return animalType.toString();
    }

    @Named("animalType")
    default AnimalType AnimalNameToAnimalType(String animalName) {
        return AnimalType.valueOf(animalName);
    }
}
