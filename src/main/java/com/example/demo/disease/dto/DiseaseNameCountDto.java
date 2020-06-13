package com.example.demo.disease.dto;

import lombok.*;

@Getter
public class DiseaseNameCountDto {

    private String name;
    private Long count;

    public DiseaseNameCountDto(String name, Long count) {
        this.name = name;
        this.count = count;
    }
}
