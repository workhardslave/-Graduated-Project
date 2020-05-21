package com.example.demo.controller;


import com.example.demo.admin.dao.AdminRepository;
import com.example.demo.admin.service.AdminService;
import com.example.demo.admin.vo.Admin;
import com.example.demo.admin.vo.AdminResponseDto;
import com.example.demo.member.controller.MemberForm;
import com.example.demo.member.dao.MemberRepository;
import com.example.demo.member.service.MemberService;
import com.example.demo.member.vo.Member;
import com.example.demo.member.vo.MemberResponseDto;
import com.example.demo.member.vo.MemberSaveRequestDto;
import com.example.demo.overlap.Address;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@Slf4j
@AllArgsConstructor
public class HomeController {

    MemberRepository memberRepository;
    MemberService memberService;

    AdminRepository adminRepository;
    AdminService adminService;

    // 회원 메인 홈
    @RequestMapping("/")
    public String home(){
        log.info("home logger");
        return "home";
    }

    // 회원가입
    @GetMapping("/member/signup")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "memberAuth/signUp";
    }






    // 회원가입 API
    @PostMapping(value = "/api/member/signup")
    public String create(@Valid MemberForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "memberAuth/signUp";
        }

        Address address = new Address(form.getCity(),
                form.getZipcode(),form.getStreet());
        MemberSaveRequestDto member = new MemberSaveRequestDto();
        member.setName(form.getName());
        member.setAddress(address);
        member.setBirth(form.getBirth());
        member.setEmail(form.getEmail());
        member.setPassword(form.getPassword());
        member.setPhone(form.getPhone());

        memberService.SignUp(member);

        return "memberAuth/signIn";
    }

    //회원정보 리스트
    @GetMapping(value = "/admin/members")
    public String list(Model model) {
        List<MemberResponseDto> members = memberService.findAllDesc();
        model.addAttribute("members", members);
        return "admin/memberList";
    }



    @GetMapping("/member/mypage")
    public String readMyData(Model model, Principal principal) {
        Member member = memberRepository.findEmailCheck(principal.getName());

        if(member != null) {
            model.addAttribute("member", member);

            /*
            System.out.println(member.getName());
            model.addAttribute("memberId", member.getId());
            model.addAttribute("memberName", member.getName());
            model.addAttribute("memberEmail", member.getEmail());
            model.addAttribute("userPassword", member.getPassword());
            model.addAttribute("userPhone", member.getPhone());
            model.addAttribute("userBirth", member.getBirth());
            model.addAttribute("userAddress", member.getAddress());
            */
        }

        return "memberAuth/myPage";
    }

    // 회원 정보수정 페이지
    @GetMapping("/member/settings/{id}")
    public String updateForm(@PathVariable Long id, Model model) {

        MemberResponseDto dto = memberService.findById(id);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        model.addAttribute("member", dto);
        System.out.println(dto.getPassword());

        return "memberAuth/settings";
    }

    // 관리자 회원정보 수정페이지
    @GetMapping("/admin/member/settings/{id}")
    public String detailList(@PathVariable Long id, Model model){

        MemberResponseDto dto = memberService.findById(id);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        model.addAttribute("member", dto);
        System.out.println(dto.getPassword());

        return "admin/settings";
    }


    //로그인 페이지
    @GetMapping("/member/login")
    public String dispLogin() throws Exception
    {

        return "memberAuth/signIn";
    }

    // 회원 로그인 결과
    @GetMapping("/member/login/result")
    public String dispLoginResult() {
        return "home";
    }

    // 회원 로그아웃
    @GetMapping("/member/logout/result")
    public String dispLogout() {

        return "home";
    }

    // 관리자 정보조회
    @GetMapping("/admin/manage")
    public String readAdminMyDate(Model model, Principal principal) {
        Admin admin = adminRepository.findEmailCheck(principal.getName());
        if(admin != null) {
            model.addAttribute("admin", admin);
        }

        return "adminAuth/admin_myPage";
    }

    // 관리자 정보수정
    @GetMapping("/admin/settings/{id}")
    public String updateAdminForm(@PathVariable Long id, Model model) {

        AdminResponseDto dto = adminService.findById(id);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        model.addAttribute("admin", dto);
        System.out.println(dto.getPassword());

        return "adminAuth/admin_settings";
    }
}