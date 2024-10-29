package com.example.service.impl;

import com.example.dto.ParrotDTO;
import com.example.entity.Parrot;
import com.example.exception.EntityNotFoundException;
import com.example.mapper.ParrotMapper;
import com.example.repository.ParrotRepository;
import com.example.service.ParrotService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ParrotServiceImpl implements ParrotService {
    private final ParrotRepository parrotRepository;
    private final ParrotMapper parrotMapper;

    @Override
    public ParrotDTO getParrotById(Long id) {
        Parrot parrot = parrotRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Parrot", id));
        return parrotMapper.parrotToParrotDTO(parrot);
    }

    @Override
    public ParrotDTO addParrot(ParrotDTO parrotDTO) {
        Parrot parrot = parrotMapper.parrotDTOtoParrot(parrotDTO);
        parrot.setId(0L);
        return parrotMapper.parrotToParrotDTO(parrotRepository.save(parrot));
    }

    @Override
    public ParrotDTO updateParrot(Long id, ParrotDTO parrotDTO) {
        if (parrotRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Parrot", id);
        }

        Parrot parrot = parrotMapper.parrotDTOtoParrot(parrotDTO);
        parrot.setId(id);
        return parrotMapper.parrotToParrotDTO(parrotRepository.save(parrot));
    }

    @Override
    public void deleteParrotById(Long id) {
        parrotRepository.deleteById(id);
    }

    @Override
    public Page<ParrotDTO> getAllParrots(Pageable pageable) {
        return parrotRepository.findAll(pageable).map(parrotMapper::parrotToParrotDTO);
    }
}
