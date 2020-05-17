package com.example.demo.member.dao;

import com.example.demo.overlap.Address;
import lombok.Getter;

@Getter
public class MemberResponseDto {

    private Long id;
    private String name;
    private String email;
    private String password;
    private Address address;
    private String phone;
    private String birth;

    public MemberResponseDto(Long id, String name, String email, String password, Address address, String phone, String birth) {
        this.id = getId();
        this.name = getName();
        this.email = getEmail();
        this.password = getPassword();
        this.address = getAddress();
        this.phone = getPhone();
        this.birth = getBirth();
    }

}
