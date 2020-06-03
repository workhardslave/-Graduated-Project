package com.example.demo.reserve.controller;

import com.example.demo.member.repository.MemberRepository;
import com.example.demo.member.vo.Member;
import com.example.demo.reserve.repository.ReserveRepository;
import com.example.demo.reserve.service.ReserveService;
import com.example.demo.reserve.vo.ReserveResponseDto;
import com.example.demo.reserve.vo.ReserveSaveRequestDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ReserveController {

    private final ReserveService reserveService;
    private final MemberRepository memberRepository;

    // 사용자 자신의 예약 정보 조회 홈페이지
    @GetMapping("/member/reserves")
    public String ReserveInfo(Model model, Principal principal) {                   // principle: session DB에 저장되어 있는 값 가져옴
        Member member = memberRepository.findEmailCheck(principal.getName()); //추후 ASPECT 적용대상
        List<ReserveResponseDto> Reserves = reserveService.findAllDesc(member);

        model.addAttribute("reserves", Reserves);
        return "members/reserves/reserveInfo";
    }

    // 사용자 자신의 예약 정보 등록 홈페이지
    @GetMapping("/member/reserves/save")
    public String ReserveSave(Model model){

        model.addAttribute("reserveForm", new ReserveForm());
        return "members/reserves/reserveSignUp";
    }

    // 사용자 병원 예약 정보 저장 API
    @PostMapping(value = "/api/member/reserve/save")
    public String Dogcreate(@Valid ReserveForm form, BindingResult result, Principal principal, Model model) {
        if (result.hasErrors()) {
            return "members/reserves/reserveSignUp";
        }

        Member member = memberRepository.findEmailCheck(principal.getName()); //추후 ASPECT 적용대상

        ReserveSaveRequestDto dto = new ReserveSaveRequestDto();
        reserveService.save(dto.builder()
                .date(form.getDate())
                .member(member)
                .description(form.getDescription())
                .dog(form.getDog_name())
                .date(form.getDate())
                .build());

        log.info(form.getDog_name());
        log.info(form.getDescription());
        log.info(form.getDate());
        log.info("member" + member);
        reserveService.save(dto);
        List<ReserveResponseDto> reserves = reserveService.findAllDesc(member);

        model.addAttribute("reserves", reserves);
        return "members/reserves/reserveInfo";
    }


    // 사용자 병원 예약 수정 및 삭제 홈페이지
    @GetMapping(value = "/reserves/settings/{id}")
    public String updateForm(@PathVariable Long id, Model model) {

        ReserveResponseDto dto = reserveService.findById(id);
        model.addAttribute("reserve", dto);

        return "members/reserves/reserveModify";
    }


    // 관리자 -> 사용자 예약 정보 조회 홈페이지
    @GetMapping("/admin/reserves")
    public String ReserveInfoAdmin(Model model) {                   // principle: session DB에 저장되어 있는 값 가져옴
        List<ReserveResponseDto> Reserves = reserveService.findAll(); //모든예약정보확인
        model.addAttribute("reserves", Reserves);
        log.info("--------------");
        log.info(Reserves.get(0).getMember().getName());
        log.info("--------------");

        return "admin/reserves/reserveInfoAdmin";
    }
}
