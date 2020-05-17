package com.example.demo.member.controller;


import com.example.demo.member.dao.MemberRepository;
import com.example.demo.member.service.MemberService;
import com.example.demo.member.vo.Member;
import com.example.demo.member.vo.MemberUpdateRequestDto;
import com.example.demo.overlap.Address;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@RestController
@AllArgsConstructor
@Slf4j
public class MemberApiController {

    MemberService memberService;

    FindByIndexNameSessionRepository sessionRepository;

    MemberRepository memberRepository;

    HttpSession session;

    // 회원정보 수정 API
    @PutMapping("/api/member/settings/{id}")
    public Long updateForm(@PathVariable Long id, @RequestBody MemberUpdateRequestDto requestDto) {

        return memberService.update(id, requestDto);
    }


    //회원정보 삭제 api
    @DeleteMapping("/api/member/delete/{id}")
    public Long delete(@PathVariable Long id, Principal principal) {

        sessionRepository.findByIndexNameAndIndexValue(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME,
                principal.getName()).keySet().forEach(session -> sessionRepository.deleteById((String) session));
        memberService.delete(id);

        return id;
    }

}
