package com.example.demo.member.vo;


import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor// 초기화 되지 않은 final 필드와 @NonNull 어노테이션이 붙은 필드에 대한 생성자 생성
public enum Role { //해당오류
    //@RequiredArgsConstructor 어노테이션에 의해 만들어짐 생성자가생성됨

    PARENT("ROLE_PARENT", "보호자"),
    DOCTOR("ROLE_DOCTOR", "수의사"), //스프링 시큐리티 권한코드에는 항상 ROLE가 있어야한다.
    USER("ROLE_ADMIN", "관리자");

    private final String key;
    private final String title;
}

