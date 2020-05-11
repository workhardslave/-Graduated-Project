package com.example.demo.APITest;


import com.example.demo.member.dao.MemberRepository;
import com.example.demo.member.dao.MemberSaveRequestDto;
import com.example.demo.member.service.MemberService;
import com.example.demo.member.vo.Member;
import com.example.demo.member.vo.Role;
import com.example.demo.overlap.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceApiTest {


    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EntityManager em;


    /**
     * 중복 확인
     */
    @Test
    public void 회원가입() throws Exception {
       //given
        Member member = new Member();
        MemberSaveRequestDto memberSaveRequestDto = new MemberSaveRequestDto();
        Address address = new Address("경기도","광명시","어딘가");

        member.builder()
                .name("현우")
                .address(address)
                .birth("1995-11-29")
                .email("gachon@mail.com")
                .password("12345")
                .phone("010-111-222")
                .role(Role.GUEST)
                .build();
        //when
        Member TestMember = memberRepository.save(member);

        em.flush();
        //then
        assertEquals(member, memberRepository.findOne(TestMember.getId()));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setEmail("abcdef@naver.com");
        //when
        memberService.SingUp(member);
        em.flush();
        //then
        fail("예외가 발생해야 한다");
    }


    @Test
    public void 로그인() throws Exception {
    }

    @Test
    public void 로그아웃() throws Exception {
    }


    @Test
    public void 회원수정() throws Exception {
    }


    @Test
    public void 회원삭제() throws Exception {
    }


}
