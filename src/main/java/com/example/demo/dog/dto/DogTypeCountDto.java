package com.example.demo.dog.dto;

import lombok.*;

@Getter
public class DogTypeCountDto {

    private String gender;
    private String type;
    private Long count;

    public DogTypeCountDto(String gender, String type, Long count) {
        this.gender = gender;
        this.type = type;
        this.count = count;
    }
}
