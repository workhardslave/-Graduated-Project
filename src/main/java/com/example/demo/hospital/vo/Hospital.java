package com.example.demo.hospital.vo;

import com.example.demo.member.vo.Member;
import com.example.demo.overlap.Address;
import com.example.demo.overlap.HospitalStatus;

import com.example.demo.reserve.vo.Reserve;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.*;

@Entity
@Getter
@NoArgsConstructor
public class Hospital {

//    @OneToMany(fetch = FetchType.LAZY)
//    List<Reserve> reserveList = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Id @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String tel;

    @Column(nullable = false)
    private String address;

    @Builder
    public Hospital(String name, String tel, String address,Member member) {
        this.name = name;
        this.tel = tel;
        this.address = address;
        this.member = member;
    }
}
