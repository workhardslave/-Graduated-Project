package com.example.demo.member.dao;

import com.example.demo.member.vo.Role;
import com.example.demo.overlap.Address;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class MemberUpdateRequestDto {

    private String password;
    private Address address;
    private String phone;

    @Builder
    public MemberUpdateRequestDto(String password, Address address, String phone) {
        this.password = password;
        this.address = address;
        this.phone = phone;
    }
}