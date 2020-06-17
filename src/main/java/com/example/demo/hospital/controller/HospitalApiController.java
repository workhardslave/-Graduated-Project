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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
    memberService.hos_delete(member.getId());
    hospitalService.delete(id);

    return id;
    }

    // 홈페이지관리자가  병원 삭제하는 api
    @DeleteMapping("/api/admin/hospital/delete/{id}")
    public Long Admin_delete(@PathVariable Long id, Principal principal) {

        HospitalResponseDto dto = hospitalService.findById(id);
        memberService.hos_delete(dto.getMember().getId()); //해당부분 테스트필요
        hospitalService.delete(id);

        return id;
    }

}
