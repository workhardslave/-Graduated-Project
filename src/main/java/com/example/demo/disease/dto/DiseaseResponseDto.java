package com.example.demo.disease.dto;

import lombok.*;

@Getter
public class DiseaseResponseDto {

    private String name;
    private String type;
    private String symptom;

    public DiseaseResponseDto(Disease entity) {
        this.name = entity.getName();
        this.type = entity.getType();
        this.symptom = entity.getSymptom();
    }
}
