package com.example.service;

import com.example.dto.CatDTO;
import com.example.entity.Cat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CatService {
    CatDTO getCatById(Long id);
    CatDTO addCat(CatDTO catDTO);
    CatDTO updateCat(Long id, CatDTO catDTO);
    void deleteCatById(Long id);
    Page<CatDTO> getAllCats(Pageable pageable);
}
