package com.example.demo.disease.dto;

import lombok.*;

@Getter
public class DiseaseResponseDto {

    private String name;
    private String symptom;
    private String type;

    public DiseaseResponseDto(Disease entity) {
        this.name = entity.getName();
        this.symptom = entity.getSymptom();
        this.type = entity.getType();
    }
}
