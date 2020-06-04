package com.example.demo.reserve.vo;

import com.example.demo.dog.dto.Dog;
import com.example.demo.member.vo.Member;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReserveResponseDto {
    private Long id;
    private Member member;
    private String date;
    private String description;
    private String name;
    private String address;
    private String tel;

    public ReserveResponseDto(Reserve entity) {
        this.id = entity.getId();
        this.member = getMember();
        this.date = entity.getDate();
        this.description = entity.getDescription();
        this.name = entity.getName();
        this.address = entity.getAddress();
        this.tel = entity.getTel();
    }
}
