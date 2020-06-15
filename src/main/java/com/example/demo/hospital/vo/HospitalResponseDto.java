package com.example.demo.hospital.vo;

import lombok.*;

@Getter
@RequiredArgsConstructor
public class HospitalResponseDto {

    private Long id;
    private String name;
    private String tel;
    private String address;

    public HospitalResponseDto(Hospital entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.tel = entity.getTel();
        this.address = entity.getAddress();
    }
}
