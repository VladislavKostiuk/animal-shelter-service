package com.example.dto;

import java.time.LocalDate;

public record CatDTO(
  Long id,
  String name,
  LocalDate birthday,
  BreedDTO breed,
  CatDTO color,
  String gender,
  String info
) {}
