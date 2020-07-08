package com.example.demo.member.vo;


import com.example.demo.config.security.Role;
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
    private Role role;


    public MemberResponseDto(Member entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.password = entity.getPassword();
        this.address = entity.getAddress();
        this.phone = entity.getPhone();
        this.birth = entity.getBirth();
        this.role = entity.getRole();
    }
}
