package com.example.demo.reserve.vo;

import com.example.demo.member.vo.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class ReserveUpdateRequestDto {

    private String date;
    private String description;


    public ReserveUpdateRequestDto(String date, String description){
        this.date = date;
        this.description = description;
    }
}
