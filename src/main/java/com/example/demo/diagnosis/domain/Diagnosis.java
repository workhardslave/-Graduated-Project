package com.example.demo.diagnosis.domain;


import com.example.demo.member.vo.Member;
import com.example.demo.reserve.vo.Reserve;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Getter
@Entity
@Embeddable
@Setter
@NoArgsConstructor
public class Diagnosis {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    private String name; //진단질병명

    private String dog;

    @OneToOne
    private Corna corna;
    @OneToOne
    private Macak macak;
    @OneToOne
    private Air air;



}
