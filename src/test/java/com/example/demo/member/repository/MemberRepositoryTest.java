package com.example.demo.member.repository;

import com.example.demo.config.security.Role;
import com.example.demo.member.domain.Address;
import com.example.demo.member.domain.Member;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @After
    public void cleanUp() {
        memberRepository.deleteAll();
    }

    @Test
    public void 회원가입_이메일_회원조회() throws Exception {
        // given
        String name = "황승환";
        String email = "hsh@gmail.com";

        String city = "수원시";
        String zipcode = "500";
        String street = "영덕대로";


        memberRepository.save(Member.builder()
            .name(name)
            .email(email)
            .address(Address.builder()
                    .city(city)
                    .zipcode(zipcode)
                    .street(street)
                    .build())
            .role(Role.GUEST) // 왜 null로 들어갈까?
            .build());

        // when
        List<Member> memberList = memberRepository.findAllDesc();
        Member memberFromEmail = memberRepository.findEmailCheck(email);

        // then
        Member member = memberList.get(0);

        assertThat(member.getName()).isEqualTo(name);
        assertThat(member.getEmail()).isEqualTo(email);
        assertThat(member.getAddress().getCity()).isEqualTo(city);
        assertThat(member.getAddress().getZipcode()).isEqualTo(zipcode);
        assertThat(member.getAddress().getStreet()).isEqualTo(street);
        assertThat(member.getRole()).isEqualTo(Role.GUEST);

        assertThat(memberFromEmail.getEmail()).isEqualTo(email);
    }


}