package com.example.demo.reserve.vo;

import com.example.demo.member.vo.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@RequiredArgsConstructor
public class Reserve  {

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
    @Column(nullable = false)

    private String tel;

    private String address; //추후 병원에서 가져온다 필요한가 ?


    @Builder
    public Reserve(Long id, Member member, String date, String description, String name, String address , String tel) {
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

