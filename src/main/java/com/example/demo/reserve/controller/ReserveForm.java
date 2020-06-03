package com.example.demo.reserve.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
public class ReserveForm {

    private String date; //방문일
    private String description;
    private String name; //병원이름
    private String dog_name; //반려견명

}
