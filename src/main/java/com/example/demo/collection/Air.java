package com.example.demo.collection;

import com.example.demo.member.vo.Member;
import com.example.demo.overlap.Result;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
