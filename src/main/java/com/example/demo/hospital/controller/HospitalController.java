package com.example.demo.hospital.controller;


import com.example.demo.hospital.repository.HospitalRepository;
import com.example.demo.hospital.service.HospitalService;
import com.example.demo.hospital.vo.Hospital;
import com.example.demo.hospital.vo.HospitalSaveRequestDto;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.member.vo.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService hospitalService;
    private final MemberRepository memberRepository;
    private final HospitalRepository hospitalRepository;

    // 병원등록 API
    @PostMapping(value = "/api/hospital/signup")
    public String create(@RequestBody HospitalSaveRequestDto Dto, BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            return "home";
        }
        Member member = memberRepository.findEmailCheck(principal.getName());
        List<Hospital> hospitals = hospitalRepository.findAllDesc();

        for(Hospital hs : hospitals){
            if(hs.getMember() == member){
                throw new IllegalStateException("병원등록은 하나만 됩니다.");
            }
        }
        HospitalSaveRequestDto hospital = new HospitalSaveRequestDto();


        hospitalService.reg(hospital.builder()
                .name(Dto.getName())
                .address(Dto.getAddress())
                .tel(Dto.getTel())
                .member(member)
                .build());

        return "memberAuth/signIn";
    }

    //controller

}
