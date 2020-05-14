package com.example.demo.member.controller;


import com.example.demo.member.dao.MemberRepository;
import com.example.demo.member.dao.MemberSaveRequestDto;
import com.example.demo.member.dao.MemberUpdateRequestDto;
import com.example.demo.member.service.MemberService;
import com.example.demo.overlap.Address;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@AllArgsConstructor
@Slf4j
public class MemberApiController {

    MemberService memberService;

    MemberRepository memberRepository;

    HttpSession session;

    //회원가입 등록 API


    //회원정보 수정 api
    @PutMapping("/api/member/update/{id}")
    public Long updateForm(@PathVariable Long id, @RequestBody MemberUpdateRequestDto requestDto) {


        return memberService.update(id, requestDto);

    }

    //회원정보 삭제
    @DeleteMapping("/api/member/delete/{id}")
    public Long delete(@PathVariable Long id) {
        memberService.delete(id);
        return id;

    }

}





