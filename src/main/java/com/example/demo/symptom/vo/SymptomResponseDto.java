package com.example.demo.symptom.vo;

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
