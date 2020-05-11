package com.example.demo.member.vo;


import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor// 초기화 되지 않은 final 필드와 @NonNull 어노테이션이 붙은 필드에 대한 생성자 생성
public enum Role { //해당오류

    GUEST("ROLE_PARENT", "보호자"),
    ADMIN("ROLE_ADMIN", "관리자");
    private final String key;
    private final String title;
}

