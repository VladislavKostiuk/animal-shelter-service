package com.example.dto;

import java.time.LocalDate;

public record DogDTO(
        Long id,
        String name,
        LocalDate birthday,
        BreedDTO breed,
        ColorDTO color,
        String gender,
        String info
)
{}
