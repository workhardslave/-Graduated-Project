package com.example.demo.member.controller;

import com.example.demo.config.security.Role;
import com.example.demo.member.domain.Address;
import com.example.demo.member.domain.Member;
import com.example.demo.member.dto.MemberSaveRequestDto;
import com.example.demo.member.dto.MemberUpdatePwd;
import com.example.demo.member.dto.MemberUpdateRequestDto;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.member.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MemberApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void 회원가입_API() throws Exception {
        // given
        String name = "황승환";
        String email = "hsh@gmail.com";
        String password = "abcd1234";


        MemberSaveRequestDto requestDto = MemberSaveRequestDto.builder()
                .name(name)
                .email(email)
                .password(password)
                .role(Role.GUEST)
                .build();

        memberService.SignUp(requestDto);

        String url = "http://localhost:" + port + "/api/member/signup";


        // when
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestDto, String.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        List<Member> members = memberRepository.findAllDesc();
        assertThat(members.get(0).getName()).isEqualTo(name);
        assertThat(members.get(0).getEmail()).isEqualTo(email);
    }

    @Test
    public void 회원수정_API() throws Exception {
        // given
        Member savedMember = memberRepository.save(Member.builder()
                .address(new Address("수원시","300","동탄원천로"))
                .phone("01067052122")
                .role(Role.GUEST)
                .build());

        Long updatedId = savedMember.getId();
        Address updatedAddress = new Address("서울시","500","테헤란로");
        String updatedPhone = "01012345678";

        MemberUpdateRequestDto updateRequestDto = MemberUpdateRequestDto.builder()
                .city(updatedAddress.getCity())
                .zipcode(updatedAddress.getZipcode())
                .street(updatedAddress.getZipcode())
                .phone(updatedPhone)
                .build();

        String url = "http://localhost:" + port + "/api/member/settings/" + updatedId;

        HttpEntity<MemberUpdateRequestDto> requestEntity = new HttpEntity<>(updateRequestDto);

        // when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Member> members = memberRepository.findAllDesc();
        // 왜 이름은 안될까?
        assertThat(members.get(0).getAddress().getCity()).isEqualTo(updatedAddress.getCity());
        assertThat(members.get(0).getPhone()).isEqualTo(updatedPhone);
    }

    @Test
    public void 패스워드수정_API() throws Exception {
        // given
        Member savedMember = memberRepository.save(Member.builder()
                .password("abcd1234")
                .role(Role.GUEST)
                .build());

        Long updatedId = savedMember.getId();
        String updatedPassword = "1234abcd";

        MemberUpdatePwd updatePwd = MemberUpdatePwd.builder()
                .password(updatedPassword)
                .password2("1234abcd")
                .build();

        String url = "http://localhost:" + port + "/api/member/settingsPwd/" + updatedId;

        HttpEntity<MemberUpdatePwd> requestEntity = new HttpEntity<>(updatePwd);

        // when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        List<Member> members = memberRepository.findAllDesc();
        assertThat(members.get(0).getPassword()).isEqualTo(updatedPassword);

    }

    @Test(expected = AssertionError.class)
    public void 회원삭제_API() throws Exception {
        // given
        Member savedMember = memberRepository.save(Member.builder()
                .email("hsh@gmail.com")
                .role(Role.GUEST)
                .build());

        Long deleteId = savedMember.getId();

        String url = "http://localhost:" + port + "/api/member/delete/" + deleteId;

        // when
        restTemplate.delete(url);

        // then
        List<Member> members = memberRepository.findAllDesc();
        assertThat(members.get(0).getId()).isEqualTo(1L); // 저장 후 회원 조회 테스트
        fail("예외가 발생해야한다.");

    }

    // 못함
    @Test
    public void 이메일중복_API() throws Exception {
        // given
        String name = "황승환";
        String email = "hsh@gmail.com";
        String password = "abcd1234";

        Member savedMember = memberRepository.save(Member.builder()
                .name(name)
                .email(email)
                .password(password)
                .role(Role.GUEST)
                .build());

        String checkEmail = "hsh@gmail.com";

        String url = "http://localhost:" + port + "/api/checkEmail";

        // when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, checkEmail, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

}