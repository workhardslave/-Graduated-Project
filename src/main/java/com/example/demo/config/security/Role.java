package com.example.demo.config.security;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@AllArgsConstructor
public enum Role {
    ADMIN("ROLE_ADMIN"),
    GUEST("ROLE_MEMBER"),
    VET("ROLE_VET");

    private String value;

}



