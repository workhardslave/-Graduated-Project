package com.example.demo.diagnosis.repository;

import com.example.demo.diagnosis.domain.Air;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirRepository extends JpaRepository<Air, Long> {
}
