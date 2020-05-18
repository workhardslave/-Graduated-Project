package com.example.demo.dog.dto;

import com.example.demo.member.vo.Member;
import com.example.demo.overlap.Address;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DogUpdateRequestDto {
    private Long id;
    private Member member;
    private String name;
    private int age;
    private String gender;
    private String birth;
    private String value;

    @Builder
    public DogUpdateRequestDto(Long id, Member member, String name, int age, String gender, String birth, String value) {
        this.id = id;
        this.member = member;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.birth = birth;
        this.value = value;
    }
}
