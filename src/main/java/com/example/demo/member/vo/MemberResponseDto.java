package com.example.demo.member.vo;


import com.example.demo.overlap.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter //추가
public class MemberResponseDto {

    private Long id;
    private String email;
    private String name;
    private String phone;
    private Address address;

    public MemberResponseDto(Member entity) {
        this.id = entity.getId();
        this.email = entity.getEmail();
        this.name = entity.getName();
        this.phone = entity.getPhone();
        this.setAddress(entity.getAddress()); //추가
    }

}
