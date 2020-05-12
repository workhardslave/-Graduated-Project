package com.example.demo.member.controller;


import com.example.demo.member.dao.MemberRepository;
import com.example.demo.member.dao.MemberSaveRequestDto;
import com.example.demo.member.service.MemberService;
import com.example.demo.member.vo.Member;
import com.example.demo.overlap.Address;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
public class MemberController {


    @Autowired
    private MemberService memberService;


    @Autowired
    private MemberRepository memberRepository;


    /**
     * @return 회원가입
     */
    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        log.info("/members/new logger");
        return "members/createMemberForm";
    }

    @PostMapping(value = "/api/members/new")
    public String create(@Valid MemberForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "members/createMemberForm";
        }

        Address address = new Address(form.getCity(), form.getStreet(),
                form.getZipcode());
        MemberSaveRequestDto member = new MemberSaveRequestDto();
        member.setName(form.getName());
        member.setAddress(address);
        member.setBirth(form.getBirth());
        member.setEmail(form.getEmail());
        member.setPassword(form.getPassword());
        member.setPhone(form.getPhone());
        memberService.SingUp(member);

        return "members/login";
    }

    /**
     * 로그인
     */


    @GetMapping("/user/login")
    public String LoginForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        log.info("/login page");
        return "/login";
    }

    // 로그인 결과 페이지
    @GetMapping("/user/login/result")
    public String dispLoginResult() {

        log.info("/login success page");
        return "/loginSuccess";
    }

    // 로그아웃 결과 페이지
    @GetMapping("/user/logout")
    public String dispLogout() {
        log.info("logout");
        return "redirect:/";
    }




    //멤버정보조회 관리자기능
    @GetMapping(value = "/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
