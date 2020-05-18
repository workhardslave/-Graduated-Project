package com.example.demo.admin.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminResponseDto {

    private Long id;
    private String name;
    private String email;
    private String password;
    private String birth;
    private String phone;

    public AdminResponseDto(Admin entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.password = entity.getPassword();
        this.birth = entity.getBirth();
        this.phone = entity.getPhone();
    }
}
