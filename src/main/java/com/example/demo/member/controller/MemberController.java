package com.example.demo.member.controller;


import com.example.demo.member.controller.MemberForm;
import com.example.demo.member.dao.MemberSaveRequestDto;
import com.example.demo.member.service.MemberService;
import com.example.demo.member.vo.Member;
import com.example.demo.overlap.Address;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

//@RequiredArgsConstructor
//@RestController
@Controller
@Slf4j
public class MemberController {


    @Autowired
    private MemberService memberService;


    /**
     * @return 회원가입
     */


    @GetMapping("/members/new")
    public String createForm(Model model) {
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
        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);
        member.setBirth(form.getBirth());
        member.setEmail(form.getEmail());
        member.setPassword(form.getPassword());
        member.setPhone(form.getPhone());
        memberService.SingUp(member);
        return "redirect:/";
    }

}
