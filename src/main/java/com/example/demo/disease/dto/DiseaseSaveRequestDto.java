package com.example.demo.disease.dto;

import lombok.*;

@NoArgsConstructor
@Getter
public class DiseaseSaveRequestDto {

    private String name;
    private String type;
    private String symptom;

    @Builder
    public DiseaseSaveRequestDto(String name, String type, String symptom) {
        this.name = name;
        this.type = type;
        this.symptom = symptom;
    }

    public Disease toEntity() {
        return Disease.builder()
                .name(name)
                .type(type)
                .symptom(symptom)
                .build();
    }
}