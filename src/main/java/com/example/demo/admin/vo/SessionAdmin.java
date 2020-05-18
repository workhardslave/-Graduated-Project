package com.example.demo.admin.vo;

import lombok.Getter;

@Getter
public class SessionAdmin {

    private String name;
    private String email;
    private String password;

    public SessionAdmin(Admin admin) {
        this.name = admin.getName();
        this.email = admin.getEmail();
        this.password = admin.getPassword();
    }
}
