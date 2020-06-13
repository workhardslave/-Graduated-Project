package com.example.demo.disease.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.sql.Array;
import java.util.ArrayList;

@Getter
@Setter
public class DiseaseForm {

    @NotEmpty(message = "증상 5개를 선택해주세요")
    private String choice;
    private ArrayList<String> symptom; // 증상을 입력받을 배열

}
