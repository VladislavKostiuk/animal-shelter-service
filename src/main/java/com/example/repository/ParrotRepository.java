package com.example.repository;

import com.example.entity.Parrot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParrotRepository extends JpaRepository<Parrot, Long> {
    Page<Parrot> findAll(Pageable pageable);
}
