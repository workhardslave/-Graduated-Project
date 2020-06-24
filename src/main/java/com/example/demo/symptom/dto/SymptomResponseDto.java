package com.example.demo.symptom.dto;

import com.example.demo.symptom.domain.Symptom;
import lombok.Getter;

@Getter
public class SymptomResponseDto {

    private Long id;
    private String name; // 증상 이름

    public SymptomResponseDto(Symptom entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }
}
