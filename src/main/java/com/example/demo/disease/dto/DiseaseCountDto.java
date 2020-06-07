package com.example.demo.disease.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiseaseCountDto {

    private String type;
    private Long count;

    public DiseaseCountDto(String type, Long count) {
        this.type = type;
        this.count = count;
    }
}
