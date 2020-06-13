package com.example.demo.collection.vo;

import com.example.demo.collection.domain.Air;
import com.example.demo.collection.domain.Corna;
import com.example.demo.collection.domain.Diagnosis;
import com.example.demo.collection.domain.Macak;
import com.example.demo.member.vo.Member;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import static javax.persistence.FetchType.LAZY;

@Getter
public class DiagnosisDto {

    private Long id;
    private Member member;
    private String name; //진단질병명
    private Corna corna;
    private Macak macak;
    private Air air;
    private String dog;

    @Builder
    public DiagnosisDto(Diagnosis entity){
        this.id = entity.getId();
        this.member = entity.getMember();
        this.name = entity.getName();
        this.corna = entity.getCorna();
        this.macak = entity.getMacak();
        this.air = entity.getAir();
        this.dog = entity.getDog();
    }
}
