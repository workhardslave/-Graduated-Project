package com.example.demo.dog.controller;



import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;


@Getter
@Setter
public class DogForm {

    @NotEmpty(message = "이름 입력은 필수입니다")
    private String name;
    @NotEmpty(message = "나이 입력은 필수입니다")
    private String age;
    @NotEmpty(message = "성별 입력은 필수입니다")
    private String gender;
    @NotEmpty(message = "생일 입력은 필수입니다")
    private String birth;
    @NotEmpty(message = "종 입력은 필수입니다")
    private String type;

}

