package com.example.demo.overlap;

import com.example.demo.disease.dto.Disease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Long> {

    //증상 시각화
    @Query("SELECT m FROM Result m where m.id = :id")
    Result findOne(Long id);

}
