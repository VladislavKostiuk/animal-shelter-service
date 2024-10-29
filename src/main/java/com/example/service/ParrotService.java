package com.example.service;


import com.example.dto.ParrotDTO;
import com.example.entity.Parrot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ParrotService {
    ParrotDTO getParrotById(Long id);
    ParrotDTO addParrot(ParrotDTO parrotDTO);
    ParrotDTO updateParrot(Long id, ParrotDTO parrotDTO);
    void deleteParrotById(Long id);
    Page<ParrotDTO> getAllParrots(Pageable pageable);
}
