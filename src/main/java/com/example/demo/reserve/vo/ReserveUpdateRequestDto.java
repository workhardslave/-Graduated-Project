package com.example.demo.reserve.vo;


import com.example.demo.dog.dto.Dog;
import com.example.demo.overlap.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ReserveUpdateRequestDto {
    private Long id;
    private Dog dog;
    private LocalDate date;
    private String description;
    private String name;
    private Address address;
    private String tel;
    private String op_time;

    public ReserveUpdateRequestDto(LocalDate date){
        this.date = date;
    }
}
