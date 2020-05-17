package com.example.demo.member.vo;

import com.example.demo.member.vo.Member;
import com.example.demo.member.vo.Role;
import com.example.demo.overlap.Address;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class MemberSaveRequestDto implements Serializable {

    private Long id;
    private String name;
    private String email;
    private String picture;
    private String password;
    private Address address;
    private Role role;
    private String birth;
    private String phone;

    @Builder
    public MemberSaveRequestDto(Long id, String name, String email, String password, Address address, Role role, String birth, String phone) {
        this.id = id;
        this.name = name; //현우
        this.email = email; //네이버주소
        this.password = password; //1234
        this.address = address;
        this.role = role; //필수
        this.phone = phone;
        this.birth = birth;
    }


    public Member toEntity(){
        return Member.builder()
                .id(id)
                .name(name)
                .email(email)
                .password(password)
                .address(address)
                .role(role)
                .birth(birth)
                .phone(phone)
                .build();
    }
}

