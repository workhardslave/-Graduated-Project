package com.example.demo.hospital.controller;

import com.example.demo.hospital.service.HospitalService;
import com.example.demo.hospital.vo.HospitalResponseDto;
import com.example.demo.hospital.vo.HospitalSaveRequestDto;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.member.vo.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HospitalController {

    private final HospitalService hospitalService;
    private final MemberRepository memberRepository;

    // 동물병원 등록 페이지
    @GetMapping("/vet/hospital/registration")
    public String registerHospital(Model model) {
        model.addAttribute("hospitalForm", new HospitalForm());

        return "hospital/registration";
    }

    // 동물병원 등록 API
    @PostMapping(value = "/api/vet/hospital/register")
    public String createHospital(@RequestBody HospitalSaveRequestDto Dto, BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            return "home";
        }

        Member member = memberRepository.findEmailCheck(principal.getName());

        if(member.getHospital() != null){
            throw new IllegalStateException("동물병원 등록은 하나만 됩니다.");
        }

        HospitalSaveRequestDto hospital = new HospitalSaveRequestDto();

        hospitalService.regHospital(hospital.builder()
                .name(Dto.getName())
                .address(Dto.getAddress())
                .tel(Dto.getTel())
                .build(), member.getId());

        return "home";
    }

    // 관리자, 전체 동물병원 조회
    @GetMapping(value = "/admin/hospitalList")
    public String allHospital(Model model) {
        List<HospitalResponseDto> Dto = hospitalService.findAllDesc();

        model.addAttribute("hospital", Dto);

        return "";
    }

    // 수의사, 동물병원 조회
    @GetMapping("/vet/myHospital")
    public String readMyHospital(Model model, Principal principal) {

        Member member = memberRepository.findEmailCheck(principal.getName());

        if(member.getHospital().getId() == null) {
            throw new IllegalStateException("동물병원 등록을 먼저 해주세요!");
        }

        HospitalResponseDto hospitalDto = hospitalService.findById(member.getHospital().getId());

        model.addAttribute("myHospital", hospitalDto);

        return "hospital/myHospital";
    }
}
