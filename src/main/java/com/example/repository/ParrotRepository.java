package com.example.repository;

import com.example.entity.Parrot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParrotRepository extends JpaRepository<Parrot, Long> {

}
