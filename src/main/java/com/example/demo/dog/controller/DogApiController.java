package com.example.demo.dog.controller;


import com.example.demo.dog.dto.DogResponseDto;
import com.example.demo.dog.dto.DogSaveRequestDto;
import com.example.demo.dog.service.DogService;
import com.example.demo.member.dao.MemberRepository;
import com.example.demo.member.service.MemberService;
import com.example.demo.member.vo.Member;
import com.example.demo.member.vo.MemberResponseDto;
import com.example.demo.member.vo.MemberSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class DogApiController {

    private final DogService dogService;
    private final MemberService memberService;
    private final MemberRepository memberRepository;


    // 강아지 정보 저장 API
    @PostMapping(value = "/member/dog/save")
    public String Dogcreate(@Valid DogForm form, BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            return "";
        }

        Member member = memberRepository.findEmailCheck(principal.getName());

        DogSaveRequestDto dog = new DogSaveRequestDto();
        dog.setAge(form.getAge());
        dog.setBirth(form.getBirth());
        dog.setGender(form.getGender());
        dog.setName(form.getName());
        dog.setValue(form.getValue());
        dog.setMember(member);
        return "";
    }
}