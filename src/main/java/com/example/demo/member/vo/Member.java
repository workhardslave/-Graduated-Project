package com.example.demo.member.vo;


import com.example.demo.overlap.Address;
import com.example.demo.overlap.BaseTimeEntity;
import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@ToString
public class Member extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;


    private String email;


    private String password;


    private String birth;


    private String phone;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private Role role;


    @Builder
    public Member(Long id, String name, Address address, Role role,String email ,String password, String birth, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.role = role;
        this.email = email;
        this.password = password;
        this.birth = birth;
        this.phone = phone;
    }

    public Member update(String password, Address address, String phone) {
        this.password = password;
        this.address = address;
        this.phone = phone;
        return this;
    }

    public String getRoleKey() {
        return this.role.getValue();
    }

}
