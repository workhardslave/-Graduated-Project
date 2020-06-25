package com.example.demo.reserve.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

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
