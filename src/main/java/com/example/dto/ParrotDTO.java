package com.example.dto;

import java.time.LocalDate;

public record ParrotDTO(
        Long id,
        String name,
        LocalDate birthday,
        BreedDTO breed,
        ColorDTO color,
        String gender,
        String info
)
{}
