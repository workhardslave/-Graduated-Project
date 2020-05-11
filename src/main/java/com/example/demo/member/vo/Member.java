package com.example.demo.member.vo;


import com.example.demo.overlap.Address;
import com.example.demo.overlap.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member extends BaseTimeEntity {



    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;


    private String email;


    private String password;


    private String birth;


    private String phone;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private Role role;


    @Builder
    public  Member(Long id, String name, Address address, Role role,String email ,String password, String birth, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.role = role;
        this.email = email;
        this.password = password;
        this.birth = birth;
        this.phone = phone;
    }


    public Member update(String password) {
        this.password = password;
        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

}
