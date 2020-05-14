package com.example.demo.member.controller;

import com.example.demo.member.vo.Role;
import com.example.demo.overlap.Address;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MemberForm {

        @NotEmpty(message = "회원 이름은 필수 입니다")
        private String name;
        @NotEmpty(message = "회원 이메일은 필수 입니다")
        private String email;
        @NotEmpty(message = "패스워드 입력은 필수 입니다")
        private String password;
        @NotEmpty(message = "생일 입력은 필수 입니다")
        private String birth;
        @NotEmpty(message = "휴대폰 입력은 필수 입니다")
        private String phone;
        @NotEmpty(message = "주소 입력은 필수 입니다")
        private String city;
        @NotEmpty(message = "주소 입력은 필수 입니다")
        private String street;
        @NotEmpty(message = "주소 입력은 필수 입니다")
        private String zipcode;

}
