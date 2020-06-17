package com.example.demo.hospital.vo;

import com.example.demo.member.vo.Member;
import com.example.demo.member.vo.Role;
import com.example.demo.overlap.Address;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
@Getter
@RequiredArgsConstructor
public class HospitalSaveRequestDto {
    private String name;
    private String address;
    private String tel;


    @Builder
    public HospitalSaveRequestDto(String name, String address,  String tel) {
        this.name = name;
        this.tel =tel;
        this.address = address;
    }


    public Hospital toEntity(){
        return Hospital.builder()
                .name(name)
                .address(address)
                .tel(tel)
                .build();
    }
}


