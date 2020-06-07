package com.example.demo.reserve.vo;

import com.example.demo.member.vo.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public class ReserveResponseDto {
    private Member member;
    private String date;
    private String description;
    private String name;
    private String address;
    private String tel;
    public ReserveResponseDto(Reserve entity) {
        this.member = entity.getMember();
        this.date = entity.getDate();
        this.description = entity.getDescription();
        this.name = entity.getName();
        this.address = entity.getAddress();
        this.tel = entity.getTel();
    }
}
