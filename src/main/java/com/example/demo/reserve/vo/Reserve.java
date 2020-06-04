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

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "hospital_name", nullable = false)
    private String name;

    @Column(name = "hospital_adrr", nullable = false)
    private String address;

    @Column(nullable = false)
    private String tel;



    @Builder
    public Reserve(Long id, Member member, String date, String description, String name, String address , String tel, String op_time) {
        this.id = id;
        this.member = member;
        this.date = date;
        this.description = description;
        this.name = name;
        this.address = address;
        this.tel = tel;
    }

    public Reserve update(String date, String description) {
        this.date = date;
        this.description = description;
        return this;
    }
}

