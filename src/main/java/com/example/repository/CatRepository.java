package com.example.repository;

import com.example.entity.Cat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatRepository extends JpaRepository<Cat, Long> {
    Page<Cat> findAll(Pageable pageable);
}
