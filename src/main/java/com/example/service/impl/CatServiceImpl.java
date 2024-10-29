package com.example.service.impl;

import com.example.dto.CatDTO;
import com.example.entity.Cat;
import com.example.exception.EntityNotFoundException;
import com.example.mapper.CatMapper;
import com.example.repository.CatRepository;
import com.example.service.CatService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class CatServiceImpl implements CatService {
    private final CatRepository catRepository;
    private final CatMapper catMapper;

    @Override
    public CatDTO getCatById(Long id) {
        Cat cat = catRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cat", id));
        return catMapper.catToCatDTO(cat);
    }

    @Override
    public CatDTO addCat(CatDTO catDTO) {
        Cat cat = catMapper.catDTOtoCat(catDTO);
        cat.setId(0L);
        return catMapper.catToCatDTO(catRepository.save(cat));
    }

    @Override
    public CatDTO updateCat(Long id, CatDTO catDTO) {
        if (catRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Cat", id);
        }

        Cat cat = catMapper.catDTOtoCat(catDTO);
        cat.setId(id);
        return catMapper.catToCatDTO(catRepository.save(cat));
    }

    @Override
    public void deleteCatById(Long id) {
        catRepository.deleteById(id);
    }

    @Override
    public Page<CatDTO> getAllCats(Pageable pageable) {
        return catRepository.findAll(pageable).map(catMapper::catToCatDTO);
    }
}
