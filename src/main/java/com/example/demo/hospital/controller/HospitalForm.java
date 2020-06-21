package com.example.demo.hospital.controller;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class HospitalForm {

    @NotEmpty(message = "이름 입력은 필수입니다")
    private String name;
    @NotEmpty(message = "전화번호 입력은 필수입니다")
    private String tel;
    @NotEmpty(message = "주소 입력은 필수입니다")
    private String address;
}
