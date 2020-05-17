package com.example.demo.member.controller;


import com.example.demo.member.dao.MemberRepository;
import com.example.demo.member.dao.MemberSaveRequestDto;
import com.example.demo.member.dao.MemberUpdateRequestDto;
import com.example.demo.member.service.MemberService;
import com.example.demo.overlap.Address;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@AllArgsConstructor
@Slf4j
public class MemberApiController {

    MemberService memberService;

    MemberRepository memberRepository;

    HttpSession session;

    //회원가입 등록 API
    @PostMapping(value = "/api/member/signup")
    public String create(@Valid MemberForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "memberAuth/signUp";
        }

        log.info(form.getEmail());
        Address address = new Address(form.getCity(), form.getStreet(),
                form.getZipcode());
        MemberSaveRequestDto member = new MemberSaveRequestDto();
        member.setName(form.getName());
        member.setAddress(address);
        member.setBirth(form.getBirth());
        member.setEmail(form.getEmail());
        member.setPassword(form.getPassword());
        member.setPhone(form.getPhone());
        memberService.SignUp(member);

        return "memberAuth/signIn";
    }

    //회원정보 수정 api
    @PutMapping("/api/member/update/{id}")
    public Long updateForm(@PathVariable Long id, @RequestBody MemberUpdateRequestDto requestDto) {

        return memberService.update(id, requestDto);

    }

    //회원정보 삭제 api
    @DeleteMapping("/api/member/delete/{id}")
    public Long delete(@PathVariable Long id) {
        memberService.delete(id);
        return id;
    }

}





