package com.example.demo.dog.dto;

import com.example.demo.member.vo.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DogUpdateRequestDto {
    private Member member;
    private String name;
    private String age;
    private String gender;
    private String birth;
    private String type;

    @Builder
    public DogUpdateRequestDto( Member member, String name, String age, String gender, String birth, String type) {
        this.member = member;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.birth = birth;
        this.type = type;
    }
}
