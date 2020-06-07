package com.example.demo.disease.dto;

import lombok.*;

@NoArgsConstructor
@Getter
public class DiseaseUpdateRequestDto {

    private String name;
    private String type;
    private String symptom;

    @Builder
    public DiseaseUpdateRequestDto(String name, String type, String symptom) {
        this.name = name;
        this.type = type;
        this.symptom = symptom;
    }
}