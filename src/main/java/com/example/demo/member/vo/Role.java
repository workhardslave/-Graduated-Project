package com.example.demo.member.vo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@AllArgsConstructor
public enum Role { //해당오류

    ADMIN("ROLE_ADMIN"),
    GUEST("ROLE_MEMBER");
    private String value;
}

