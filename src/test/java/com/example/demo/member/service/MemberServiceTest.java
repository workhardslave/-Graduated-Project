package com.example.demo.member.service;

import com.example.demo.config.security.Role;
import com.example.demo.member.domain.Address;
import com.example.demo.member.domain.Member;
import com.example.demo.member.dto.MemberResponseDto;
import com.example.demo.member.dto.MemberSaveRequestDto;
import com.example.demo.member.dto.MemberUpdatePwd;
import com.example.demo.member.dto.MemberUpdateRequestDto;
import com.example.demo.member.repository.MemberRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;

    @PersistenceContext
    EntityManager em;

    @Test
    public void 회원가입() throws Exception {
        // given
        String name = "황승환";
        String email = "hsh@gmail.com";
        String password = "asdf1234";

        Address address = new Address("수원시","500","영덕대로");

        // when
        Long saveId = memberService.SignUp(MemberSaveRequestDto.builder()
                .name(name)
                .password(password)
                .email(email)
                .address(address)
                .role(Role.GUEST)
                .build());

        // then
        Member member = memberService.findMember(saveId);
        List<MemberResponseDto> memberDto = memberService.findAllDesc();
        assertThat(member).isEqualTo(memberRepository.getOne(saveId)); // 저장 후 회원 조회 테스트
        assertThat(memberDto.get(0).getId()).isEqualTo(member.getId()); // 저장 후 전체 회원 조회
    }


    @Test
    public void 회원수정() throws Exception {
        // given
        String email = "hsh@gmail.com";
        String password = "asdf1234";

        Address address = new Address("수원시","500","영덕대로");

        Long saveId = memberService.SignUp(MemberSaveRequestDto.builder()
                .password(password)
                .email(email)
                .address(address)
                .role(Role.GUEST)
                .build());


        // when
        Long updateId = memberService.update(saveId, MemberUpdateRequestDto.builder()
                .city("서울시")
                .zipcode("800")
                .street("테헤란로")
                .build());

        // then
        Member member = memberService.findMember(updateId);
        assertThat(member.getAddress().getCity()).isEqualTo("서울시");
        assertThat(member.getAddress().getZipcode()).isEqualTo("800");
        assertThat(member.getAddress().getStreet()).isEqualTo("테헤란로");

    }

    @Test
    public void 패스워드_수정() throws Exception {
        // given
        String email = "hsh@gmail.com";
        String password = "asdf1234";
        Long savedId = memberService.SignUp(MemberSaveRequestDto.builder()
                .email(email)
                .password(password)
                .build());

        Member m = em.find(Member.class, savedId);

        // when
        memberService.updatePwd(m.getId(), MemberUpdatePwd.builder()
                .password("asdf12345")
                .build());


        System.out.println("m.getPassword() = " + m.getPassword()); //바귄비번


        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        boolean matches = passwordEncoder.matches("asdf12345",m.getPassword());
        assertThat(matches).isEqualTo(true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void 회원삭제() throws Exception {
        // given
        String name = "황승환";
        String email = "hsh@gmail.com";
        String password = "asdf1234";

        Address address = new Address("수원시","500","영덕대로");

        Long saveId = memberService.SignUp(MemberSaveRequestDto.builder()
                .name(name)
                .password(password)
                .email(email)
                .address(address)
                .role(Role.GUEST)
                .build());

        // when
        Long deleteId = memberService.delete(saveId);

        // then
        assertThat(memberService.findMember(deleteId).getId()).isEqualTo(1L); // 저장 후 회원 조회 테스트
        fail("예외가 발생해야한다.");
    }
    
    @Test
    public void 이메일_중복체크() throws Exception {
        // given
        String email1 = "hsh@gmail.com";
        String password1 = "asdf1234";
        String email2 = "hsh@gmail.com";

        // when
        memberService.SignUp(MemberSaveRequestDto.builder()
                .email(email1)
                .password(password1)
                .build());
        int check = memberService.validateDuplicateMember(email2);

        // then
        assertThat(check).isEqualTo(1);

    }

}