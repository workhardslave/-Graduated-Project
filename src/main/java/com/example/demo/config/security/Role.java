package com.example.demo.config.security;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@AllArgsConstructor
public enum Role {
    ADMIN("ROLE_ADMIN","ADMIN"),
    GUEST("ROLE_MEMBER","GUEST"),
    VET("ROLE_VET", "VET");

    private final String value;
    private final String title;

}



