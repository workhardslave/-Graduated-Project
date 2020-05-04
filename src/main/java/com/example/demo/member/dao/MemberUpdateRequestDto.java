package com.example.demo.member.dao;

import com.example.demo.member.vo.Role;
import com.example.demo.overlap.Address;
import lombok.Builder;
import lombok.Getter;


@Getter
public class MemberUpdateRequestDto {

    private String name;
    private String email;
    private int password;
    private Address address;
    private Role role;
    private String phone;
    private String birth;

    @Builder
    public MemberUpdateRequestDto(String name, String email, int password, Address address, Role role, String phone, String birth) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.role = role;
        this.phone = phone;
        this.birth = birth;
    }
}