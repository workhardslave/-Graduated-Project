package com.example.demo.member.vo;


import com.example.demo.overlap.BaseTimeEntity;
import lombok.Getter;


import java.io.Serializable;

@Getter
public class SessionUser extends BaseTimeEntity implements Serializable  {
    private String name;
    private String email;
    private String password;

    public SessionUser(Member member) {
        this.name = member.getName();
        this.email = member.getEmail();
        this.password = member.getPassword();
    }

}
