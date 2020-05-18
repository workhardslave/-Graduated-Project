package com.example.demo.dog.dto;


import com.example.demo.dog.Dog;
import com.example.demo.member.vo.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter

public class DogSaveRequestDto {
    private Long id;
    private Member member;
    private String name;
    private int age;
    private String gender;
    private String birth;
    private String value;


    @Builder
    public DogSaveRequestDto(Long id, Member member, String name, int age, String gender, String birth, String value) {
        this.id = id;
        this.member = member;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.birth = birth;
        this.value = value;
    }
    public Dog toEntity(){
        return Dog.builder()
                .id(id)
                .member(member)
                .name(name)
                .age(age)
                .gender(gender)
                .birth(birth)
                .value(value)
                .build();
    }

}
