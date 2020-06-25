package com.example.demo.symptom.repository;

import com.example.demo.symptom.domain.Symptom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SymptomRepository extends JpaRepository<Symptom, Long> {

    @Query("SELECT s FROM Symptom s ORDER BY s.id DESC ")
    List<Symptom> findAllDesc();
}
