package com.example.demo.member.vo;


import lombok.Getter;


@Getter
public class MemberResponseDto {

    private Long id;
    private String email;
    private String name;
    private String phone;

    public MemberResponseDto(Member entity) {
        this.id = entity.getId();
        this.email = entity.getEmail();
        this.name = entity.getName();
        this.phone = entity.getPhone();
    }

}
