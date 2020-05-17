package com.example.demo.admin.vo;

import com.example.demo.overlap.BaseTimeEntity;
import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@ToString
public class Admin extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    private String birth;

    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public Admin(Long id, String name, String email, String password, String birth, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.birth = birth;
        this.phone = phone;
    }

    public Admin update(String password, String phone) {
        this.password = password;
        this.phone = phone;
        return this;
    }

    public String getRoleKey() {
        return this.role.getValue();
    }
}
