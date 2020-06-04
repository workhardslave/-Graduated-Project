package com.example.demo.dog.vo;

import lombok.Getter;


@Getter
public class DogCountDto {

    private String gender;
    private Long count;

    public DogCountDto(String gender, Long count) {
        this.gender = gender;
        this.count = count;
    }
}
