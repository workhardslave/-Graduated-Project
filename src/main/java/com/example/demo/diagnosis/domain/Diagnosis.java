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
    private Member member;

    @OneToOne
    @JoinColumn(name = "reserve_id")
    private Reserve reserve;

    private String name; //진단질병명

    private String dog;
    @ManyToOne
    private Corna corna;

    @ManyToOne
    private Macak macak;

    @ManyToOne
    private Air air;



}
