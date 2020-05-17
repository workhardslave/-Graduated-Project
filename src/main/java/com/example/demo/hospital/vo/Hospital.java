package com.example.demo.hospital.vo;


import com.example.demo.member.vo.Member;
import com.example.demo.overlap.Address;
import com.example.demo.overlap.HospitalStatus;

import lombok.Getter;

import javax.persistence.*;


@Entity
@Getter
public class Hospital {

    @Id @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "hospital_id", nullable = false)
    private Long id;

    @Column(name = "hospital_name", nullable = false)
    private String name;


    @Embedded
    @Column(nullable = false)
    private Address address;

    @Column(nullable = false)
    private String tel;

    @Column(nullable = false)
    private String op_time; //운영시간

    @Enumerated(EnumType.STRING)
    private HospitalStatus status;


}
