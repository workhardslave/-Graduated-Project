package com.example.demo.admin.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {

    ADMIN("ROLE_ADMIN"),
    GUEST("ROLE_MEMBER");
    private String value;
}

