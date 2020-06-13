package com.example.demo.overlap;


import com.example.demo.collection.Air;
import com.example.demo.collection.Corna;
import com.example.demo.collection.Macak;
import com.example.demo.member.vo.Member;
import com.google.gson.JsonElement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Getter
@Entity
@Embeddable
@Setter
@NoArgsConstructor
public class Result {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = LAZY)
    private Member member;
    private String name; //진단질병명


    @ManyToOne
    private Corna corna;

    @ManyToOne
    private Macak macak;

    @ManyToOne
    private Air air;



}
