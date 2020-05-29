package com.example.demo.reserve.vo;


import com.example.demo.member.vo.Member;
import com.example.demo.overlap.Address;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Reserve {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Column(name = "visit_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @NotEmpty
    @Column(name = "description")
    private String description;

    @Column(name = "hospital_name", nullable = false)
    private String name;

    @Embedded
    @Column(nullable = false)
    private Address address;

    @Column(nullable = false)
    private String tel;

    @Column(nullable = false)
    private String op_time; //운영시간

    @Builder
    public Reserve(Long id, Member member, LocalDate date, String description, Address address , String tel, String op_time) {
        this.id = id;
        this.member = member;
        this.date = date;
        this.description = description;
        this.address = address;
        this.tel = tel;
        this.op_time = op_time;
    }
}

