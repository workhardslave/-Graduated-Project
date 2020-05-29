package com.example.demo.reserve.controller;

import com.example.demo.dog.dto.DogResponseDto;
import com.example.demo.member.dao.MemberRepository;
import com.example.demo.member.vo.Member;
import com.example.demo.reserve.service.ReserveService;
import com.example.demo.reserve.vo.ReserveResponseDto;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

public class ReserveController {

    ReserveService reserveService;
    MemberRepository memberRepository;

    // 사용자 자신의 예약 정보 조회 홈페이지
    @GetMapping("/member/reserves")
    public String ReserveInfo(Model model, Principal principal) {                   // principle: session DB에 저장되어 있는 값 가져옴
        Member member = memberRepository.findEmailCheck(principal.getName());
        List<ReserveResponseDto> Reserves = reserveService.findAllDesc(member);

        model.addAttribute("reserves", Reserves);
        return "members/reserves/reserveInfo";
    }
}
