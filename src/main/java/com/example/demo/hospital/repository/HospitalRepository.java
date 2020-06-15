package com.example.demo.hospital.repository;

import com.example.demo.hospital.vo.Hospital;
import org.springframework.data.jpa.repository.Query;

import java.util.*;

public interface HospitalRepository {

    @Query("SELECT ho FROM Hospital ho ORDER BY ho.id DESC")
    List<Hospital> findAllDesc();
}
