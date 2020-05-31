package com.example.demo.reserve.vo;

import com.example.demo.member.vo.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Reserve {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Column(name = "visit_date", nullable = false)
    private String date;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "hospital_name")
    private String name;

    @Column
    private String address; //추후 병원에서 가져온다 필요한가 ?

    private String tel; //추후 병원에서가져온다 필요한가 ?

    @Column
    private String op_time; //추후 병원에서 가져옴 필요한가?

    @Column(nullable = false)
    private String dog; //예약강아지


    @Builder
    public Reserve(Long id, Member member, String date, String description, String name, String address , String tel, String op_time, String dog) {
        this.id = id;
        this.member = member;
        this.date = date;
        this.description = description;
        this.name = name;
        this.address = address;
        this.tel = tel;
        this.op_time = op_time;
        this.dog = dog;
    }

    public Reserve update(String date, String description) {
        this.date = date;
        this.description = description;
        return this;
    }
}

