package com.example.demo.disease.dto;

import lombok.*;

@Getter
public class DiseaseResponseDto {

    private String name;
    private String description;

    public DiseaseResponseDto(Disease entity) {
        this.name = entity.getName();
        this.description = entity.getDescription();
    }
}
