package com.example.demo.disease.dao;

import com.example.demo.disease.dto.Disease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.*;

public interface DiseaseRepository extends JpaRepository<Disease, Long> {

    @Query("SELECT di FROM Disease di ORDER BY di.id DESC")
    List<Disease> findAllDesc();
}
