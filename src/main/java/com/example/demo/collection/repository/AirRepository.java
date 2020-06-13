package com.example.demo.collection.repository;

import com.example.demo.collection.domain.Air;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirRepository extends JpaRepository<Air, Long> {
}
