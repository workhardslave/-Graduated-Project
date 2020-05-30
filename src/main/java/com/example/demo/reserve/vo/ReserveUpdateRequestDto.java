package com.example.demo.reserve.vo;

import com.example.demo.member.vo.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ReserveUpdateRequestDto {
    private Long id;
    private Member member;
    private String date;
    private String description;
    private String name;
    private String address;
    private String tel;
    private String op_time;

    public ReserveUpdateRequestDto(String date, String description){
        this.date = date;
        this.description = description;
    }
}
