package com.example.demo.member.vo;


import com.example.demo.member.vo.Role;
import com.example.demo.overlap.Address;
import lombok.Builder;
import lombok.Getter;


@Getter
public class MemberUpdateRequestDto {

    private Long id;
    private String name;
    private String email;
    private String password;
    private Address address;
    private String phone;
    private String birth;

    @Builder
    public MemberUpdateRequestDto(Long id, String name, String email, String password, Address address,String phone, String birth) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.birth = birth;
    }
}