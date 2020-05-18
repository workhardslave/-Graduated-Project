package com.example.demo.admin.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdminUpdateRequestDto {

    private Long id;
    private String name;
    private String email;
    private String password;
    private String birth;
    private String phone;

    @Builder
    public AdminUpdateRequestDto(String password, String phone) {
        this.password = password;
        this.phone = phone;
    }
}
