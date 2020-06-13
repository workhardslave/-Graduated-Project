package com.example.demo.diagnosis.dao;

import com.example.demo.diagnosis.dto.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.*;

public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {

    @Query("SELECT diag FROM Diagnosis diag ORDER BY diag.id DESC")
    List<Diagnosis> findAllDesc();
}
