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

    FindByIndexNameSessionRepository sessionRepository;

    MemberService memberService;

    MemberRepository memberRepository;

    HttpSession session;

    // 회원이 직접정보를 수정하는 API
    @PutMapping("/api/member/settings/{id}")
    public Long updateForm(@PathVariable Long id, @RequestBody MemberUpdateRequestDto requestDto) {

        return memberService.update(id, requestDto);
    }


    //회원이 직접정보를 삭제하는 api
    @DeleteMapping("/api/member/delete/{id}")
    public Long delete(@PathVariable Long id, Principal principal) {
        sessionRepository.findByIndexNameAndIndexValue(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME,
                principal.getName()).keySet().forEach(session -> sessionRepository.deleteById((String) session));
        memberService.delete(id);

        return id;
    }

    // 관리자가 회원정보를 수정하는 API
    @PutMapping("/api/admin/member/settings/{id}")
    public Long updateMember(@PathVariable Long id, @RequestBody MemberUpdateRequestDto requestDto) {

        return memberService.update(id, requestDto);
    }


    //관리자가 회원정보를 삭제하는 api
    @DeleteMapping("/api/admin/member/delete/{id}")
    public Long deleteMember(@PathVariable Long id) {
        memberService.delete(id);
        return id;
    }

}