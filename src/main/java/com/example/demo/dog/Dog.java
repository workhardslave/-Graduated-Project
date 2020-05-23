package com.example.demo.dog;


import com.example.demo.member.vo.Member;
import com.example.demo.overlap.Address;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Dog {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
    private String name;

    private int age;
    private String gender;
    private String birth;
    private String type;


    @Builder
    public Dog(Long id, Member member, String name, int age, String gender, String birth, String type) {
        this.id = id;
        this.member=member;
        this.name = name;
        this.age = age;
        this.gender=gender;
        this.birth=birth;
        this.type=type;
    }

    public Dog update(int age, String type, String name) {
        this.age = age;
        this.type = type;
        this.name = name;

        return this;
    }
}
