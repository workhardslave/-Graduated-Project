package com.example.demo.disease.dto;

import lombok.*;

@Getter
@Setter
public class DiseaseResponseDto {

    private Long id;
    private String name;
    private String type;
    private String symptom;

    public DiseaseResponseDto(Disease entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.type = entity.getType();
        this.symptom = entity.getSymptom();
    }
}
