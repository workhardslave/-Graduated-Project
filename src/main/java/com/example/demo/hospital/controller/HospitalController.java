package com.example.demo.hospital.controller;

import com.example.demo.hospital.repository.HospitalRepository;
import com.example.demo.hospital.service.HospitalService;
import com.example.demo.hospital.vo.Hospital;
import com.example.demo.hospital.vo.HospitalResponseDto;
import com.example.demo.hospital.vo.HospitalSaveRequestDto;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.member.vo.Member;
import com.example.demo.reserve.service.ReserveService;
import com.example.demo.reserve.vo.ReserveResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HospitalController {

    private final HospitalService hospitalService;
    private final ReserveService reserveService;

    private final MemberRepository memberRepository;
    private final HospitalRepository hospitalRepository;

    // 동물병원 등록 페이지
    @GetMapping("/vet/hospital/registration")
    public String registerHospital(Model model) {
        model.addAttribute("hospitalForm", new HospitalForm());

        return "hospital/registration";
    }

    // 동물병원 등록 API
    @PostMapping(value = "/api/vet/hospital/register")
    public Long createHospital(@RequestBody HospitalSaveRequestDto Dto, BindingResult result, Principal principal) {


        Member member = memberRepository.findEmailCheck(principal.getName());

        if(member.getHospital() != null){
            throw new IllegalStateException("동물병원 등록은 하나만 됩니다.");
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

    // 관리자, 전체 동물병원 조회
    @GetMapping(value = "/admin/hospitalList")
    public String allHospital(Model model) {
        List<HospitalResponseDto> Dto = hospitalService.findAllDesc();

        model.addAttribute("hospital", Dto);

        return "admin/hospital/hospitalList";
    }

    // 관리자, 동물병원 조회
    @GetMapping("/member/hospital/settings/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        HospitalResponseDto dto = hospitalService.findById(id);
        model.addAttribute("hos", dto);
        return "admin/hospital/hospitalDetail";
    }

    // 수의사, 동물병원 조회
    @GetMapping("/vet/myHospital")
    public String readMyHospital(Model model, Principal principal) {

        Member member = memberRepository.findEmailCheck(principal.getName());

        if(member.getHospital() == null) {
            return "home";      // 동물병원 등록 페이지 redirect
        }

        HospitalResponseDto hospitalDto = hospitalService.findById(member.getHospital().getId());

        model.addAttribute("myHospital", hospitalDto);

        return "hospital/myHospital";
    }

    // 수의사, 동물병원 예약 조회
    @GetMapping("/vet/hospital/reservationList")
    public String readMyReservation(Model model, Principal principal) {

        Member member = memberRepository.findEmailCheck(principal.getName());
        Hospital hospital = hospitalRepository.findOne(member.getHospital().getId());
        List<ReserveResponseDto> Reserves = reserveService.findAllHospital(hospital);

        model.addAttribute("reserves", Reserves);

        return "hospital/reservationList";
    }

    // 수의사, 동물병원 예약 수정
    @GetMapping("/vet/hospital/reservation/{id}")
    public String updateEachReservation(@PathVariable Long id, Model model) {

        ReserveResponseDto reserveDto = reserveService.findById(id);

        model.addAttribute("reserves", reserveDto);

        return "hospital/reservationSettings";
    }
}
