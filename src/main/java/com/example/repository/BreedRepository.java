package com.example.repository;

import com.example.entity.Breed;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BreedRepository extends JpaRepository<Breed, Long> {
    Page<Breed> findAll(Pageable pageable);
}
