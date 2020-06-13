package com.example.demo.diagnosis.dto;

import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@ToString
@Entity
public class Diagnosis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;            // 진단 질병명
    private String symptom;         // 사용자가 입력한 증상
    private String type;            // 진단 질병타입
    private String description;     // 진단 질병설명

    @Builder
    public Diagnosis(Long id, String name, String symptom, String type, String description) {
        this.id = id;
        this.name = name;
        this.symptom = symptom;
        this.type = type;
        this.description = description;
    }
}