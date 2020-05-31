package com.example.demo.reserve.vo;

import com.example.demo.dog.dto.Dog;
import com.example.demo.member.vo.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.servlet.tags.EditorAwareTag;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
public class ReserveSaveRequestDto {

    private Long id;

    private Member member;

    private String date;

    private String description;

    private String name;

    private String address;

    private String tel;

    private String op_time;
    private String dog;

    @Builder
    public ReserveSaveRequestDto(Long id, Member member, String date, String description, String name, String address, String tel, String op_time,String dog) {
        this.id = id;
        this.member = member;
        this.date = date;
        this.description = description;
        this.name = name;
        this.address = address;
        this.tel = tel;
        this.op_time = op_time;
        this.dog = dog;
    }
    public Reserve toEntity(){
        return Reserve.builder()
                .member(member)
                .name(name)
                .date(date)
                .address(address)
                .op_time(op_time)
                .tel(tel)
                .description(description)
                .dog(dog)
                .build();
    }
}
