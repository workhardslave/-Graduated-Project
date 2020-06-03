package com.example.demo.member.vo;


import com.example.demo.overlap.Address;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
public class MemberResponseDto {

    private Long id;
    private String name;
    private String email;
    private String password;
    private Address address;
    private String phone;
    private String birth;


    public MemberResponseDto(Member entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.password = entity.getPassword();
        this.address = entity.getAddress();
        this.phone = entity.getPhone();
        this.birth = entity.getBirth();
    }
}
