package com.example.demo.collection.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Air {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String percent;

    @Builder
    public Air(String percent) {
        this.percent = percent;
    }
}
