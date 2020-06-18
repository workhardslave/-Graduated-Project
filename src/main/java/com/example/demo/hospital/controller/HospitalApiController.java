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

    // 수의사, 병원 삭제하는 api
    @DeleteMapping("/api/vet/hospital/delete/{hospital_id}")
    public Long deleteVetHospital(@PathVariable Long hospital_id, Principal principal) {

        Member member = memberRepository.findEmailCheck(principal.getName());

        member.setHospital(null);                       // JPA에서 알아서 감지해서 null 값 세팅
        hospitalService.deleteHospital(hospital_id);

        return hospital_id;
    }

//    // 관리자, 병원 삭제하는 api
//    @DeleteMapping("/api/admin/hospital/delete/{hospital_id}")
//    public Long deleteAdminHospital(@PathVariable Long hospital_id) {
//
//        HospitalResponseDto hospitalDto = hospitalService.findById(hospital_id);
//
//        memberService.deleteMemHospital(hospitalDto.getId()); // 해당 부분 테스트 필요
//        hospitalService.deleteHospital(hospital_id);
//
//        return hospital_id;
//    }
}
