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

    @Id @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "hospital_id", nullable = false)
    private Long id;

    @Column(name = "hospital_name", nullable = false)
    private String name;

    @Column(name = "hospital_tel", nullable = false)
    private String tel;

    @Column(name = "hospital_address", nullable = false)
    private String address;

    @OneToOne
    @JoinColumn(name ="member_id")
    private Member member;

    @Builder
    public Hospital(String name, String tel, String address, Member member) {
        this.name = name;
        this.tel = tel;
        this.address = address;
        this.member = member;
    }
}