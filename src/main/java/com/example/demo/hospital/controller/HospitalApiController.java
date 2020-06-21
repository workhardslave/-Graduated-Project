package com.example.demo.hospital.controller;


import com.example.demo.hospital.service.HospitalService;
import com.example.demo.hospital.vo.HospitalResponseDto;
import com.example.demo.hospital.vo.HospitalSaveRequestDto;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.member.service.MemberService;
import com.example.demo.member.vo.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class HospitalApiController {


    private final HospitalService hospitalService;
    private final MemberRepository memberRepository;
    private final MemberService memberService;
    // 병원관리자 병원 삭제하는 api
    @DeleteMapping("/api/member/hospital/delete/{id}")
    public Long delete(@PathVariable Long id, Principal principal) {
    Member member =  memberRepository.findEmailCheck(principal.getName());
    member.regHospital(null);
    hospitalService.delete(id);

    return id;
    }

    // 홈페이지관리자가  병원 삭제하는 api
    @DeleteMapping("/api/admin/hospital/delete/{id}")
    public Long Admin_delete(@PathVariable Long id, Principal principal) {

        Member member = memberRepository.findEmailCheck(principal.getName());
        member.regHospital(null);
        hospitalService.delete(id);

        return id;
    }

    // 병원등록 API
    @PostMapping(value = "/api/hospital/signup")
    public Long createHospital(@RequestBody HospitalSaveRequestDto Dto, Principal principal) {

        Member member = memberRepository.findEmailCheck(principal.getName());

        if(member.getHospital() != null){
            throw new IllegalStateException("병원등록은 하나만 됩니다.");
        }

        HospitalSaveRequestDto hospital = new HospitalSaveRequestDto();

        Long id = hospitalService.reg(hospital.builder()
                .name(Dto.getName())
                .address(Dto.getAddress())
                .tel(Dto.getTel())
                .member(member)
                .build(), member);


        return id;
    }

}
