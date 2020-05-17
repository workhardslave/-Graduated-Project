package com.example.demo.controller;


import com.example.demo.member.controller.MemberForm;
import com.example.demo.member.dao.MemberRepository;
import com.example.demo.member.service.MemberService;
import com.example.demo.member.vo.Member;
import com.example.demo.member.vo.MemberResponseDto;
import com.example.demo.member.vo.MemberSaveRequestDto;
import com.example.demo.overlap.Address;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@Slf4j
@AllArgsConstructor
public class HomeController {

    MemberRepository memberRepository;
    MemberService memberService;


    //메인페이지
    @RequestMapping("/")
    public String home(){
        log.info("home logger");
        return "home";
    }

    //회원가입 페이지
    @GetMapping("/member/signup")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "memberAuth/signUp";
    }


    @PostMapping(value = "/api/member/signup")
    public String create(@Valid MemberForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "memberAuth/signUp";
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
        memberService.SignUp(member);

        return "memberAuth/signIn";
    }


    //회원정보 리스트
    @GetMapping(value = "/member/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "memberAuth/memberList";
    }

    //내정보
    /*
    @GetMapping("/member/Info")
    public String postsMyData(Model model, Principal principal) {

        Member member = memberRepository.findEmailCheck(principal.getName());

        if (member != null) { // 세션에 저장된 값이 있을때만 model에 userName으로 등록한다.
            //세션에 저장된 값이 없으면 model엔 아무런 값이 없는 상태이니 로그인 버튼이 보인다.
            model.addAttribute("userName", member.getName());
            model.addAttribute("userEmail", member.getEmail());
            model.addAttribute("userPhone", member.getPhone());

        }
        return "members/Info";
    }
    */

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

    //회원정보 수정페이지
    @GetMapping("/member/settings/{id}")
    public String updateForm(@PathVariable Long id, Model model) {

        MemberResponseDto dto = memberService.findById(id);
        model.addAttribute("member", dto);

        return "memberAuth/settings";
    }

    /*
    @PutMapping("/member/settings/{id}")
    public Long update(@PathVariable Long id, @RequestBody MemberUpdateRequestDto memberUpdateRequestDto) {
        return memberService.update(id, memberUpdateRequestDto);
    }

    @GetMapping("/member/settings/{id}")
    public MemberUpdateRequestDto findById(@PathVariable Long id) {
        return memberService.findById(id);
    }
    */

    //로그인 페이지
    @GetMapping("/member/login")
    public String dispLogin(Principal principals) throws Exception
    {

        return "memberAuth/signIn";
    }

    //로그인 결과

    @GetMapping("/member/login/result")
    public String dispLoginResult(Principal principal) {

//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        UserDetails userDetails = (UserDetails)principal;
//        String username = ((UserDetails) principal).getUsername();
//
//        System.out.println(username);
//        System.out.println(principals.getName());
        return "home";
    }


    //로그아웃
    @GetMapping("/member/logout/result")
    public String dispLogout() {

        return "home";
    }

}