package com.example.demo.disease.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class DiseaseForm {

    @NotEmpty(message = "증상 5개를 선택해주세요")
    private String choice;
    private String name1;
    private String name2;
    private String name3;
    private String name4;
    private String name5;

}
