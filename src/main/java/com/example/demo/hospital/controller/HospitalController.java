package com.example.demo.hospital.controller;

import com.example.demo.hospital.repository.HospitalRepository;
import com.example.demo.hospital.service.HospitalService;
import com.example.demo.hospital.vo.Hospital;
import com.example.demo.hospital.vo.HospitalResponseDto;
import com.example.demo.hospital.vo.HospitalSaveRequestDto;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.member.vo.Member;
import com.example.demo.member.vo.MemberResponseDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService hospitalService;
    private final MemberRepository memberRepository;

    // 병원 등록 페이지
    @GetMapping("/hospital/registeration")
    public String registeration() {

        return "hospital/registeration";
    }

    // 병원등록 API
    @PostMapping(value = "/api/hospital/signup")
    public String createHospital(@RequestBody HospitalSaveRequestDto Dto, BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            return "home";
        }

        Member member = memberRepository.findEmailCheck(principal.getName());

        if(member.getHospital() != null){
            throw new IllegalStateException("병원등록은 하나만 됩니다.");
        }

        HospitalSaveRequestDto hospital = new HospitalSaveRequestDto();

        hospitalService.reg(hospital.builder()
                .name(Dto.getName())
                .address(Dto.getAddress())
                .tel(Dto.getTel())
                .build(), member.getId());

        return "";
    }

    // 관리자 -> 병원 전체목록 조회
    @GetMapping(value = "/admin/hospitalList")
    public String allHospital(Model model) {
        List<HospitalResponseDto> Dto = hospitalService.findAllDesc();
        model.addAttribute("hospital",Dto);

        return "";
    }

<<<<<<< HEAD
    //수의사 내 병원정보 페이지
    @GetMapping("/member/myhospital")
    public String readHospitalData(Model model, Principal principal) {
=======
    // 수의사 내 병원정보 페이지
    @GetMapping("/member/hospital/mypage")
    public String readHospital(Model model, Principal principal) {
>>>>>>> 19081d2a718f0299655464add2f628e80bb263f4
        Member member = memberRepository.findEmailCheck(principal.getName()); //추후 ASPECT 적용대상

        HospitalResponseDto hos = hospitalService.findById(member.getHospital().getId());

        model.addAttribute("hospital", hos);


        return "";
    }

    // 수의사, 자신의 동물병원 조회
    @GetMapping("/member/myhospital")
    public String readMyHospital(Model model, Principal principal) {

        Member member = memberRepository.findEmailCheck(principal.getName());

<<<<<<< HEAD
    // 병원 정보수정 페이지(관리자, 수의사 공통)
    @GetMapping("/member/hospital/settings/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        HospitalResponseDto dto = hospitalService.findById(id);
        model.addAttribute("hospital", dto);
        return "";
    }





=======
        if(member != null) {
            Hospital hospital = hospitalRepository.findMember(member);
>>>>>>> 19081d2a718f0299655464add2f628e80bb263f4

            model.addAttribute("myHospital", hospital);
        }

        return "hospital/myHospital";
    }

}
