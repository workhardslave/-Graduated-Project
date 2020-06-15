package com.example.demo.diagnosis.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Macak {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String percent;

    @OneToMany(mappedBy="air", orphanRemoval = true)
    List<Diagnosis> diList = new ArrayList<>();


    @Builder
    public Macak(String percent) {
        this.percent = percent;
    }
}
