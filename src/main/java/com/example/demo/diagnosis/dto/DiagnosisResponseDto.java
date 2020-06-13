package com.example.demo.diagnosis.dto;

import lombok.*;

@Getter
public class DiagnosisResponseDto {

    private String name;
    private String symptom;
    private String type;
    private String description;

    public DiagnosisResponseDto(Diagnosis entity) {
        this.name = entity.getName();
        this.symptom = entity.getSymptom();
        this.type = entity.getType();
        this.description = entity.getDescription();
    }
}
