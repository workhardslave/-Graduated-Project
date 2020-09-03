package com.example.demo.hospital.dto;

import com.example.demo.hospital.domain.Hospital;
import com.example.demo.member.domain.Member;
import lombok.Getter;

@Getter
public class HospitalResponseDto {

    private Long id;
    private String name;
    private String tel;
    private String address;
    private Member member;

    public HospitalResponseDto(Hospital entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.tel = entity.getTel();
        this.address = entity.getAddress();
        this.member =entity.getMember();
    }
}
