package com.example.demo.controller;


import com.example.demo.member.controller.MemberForm;
import com.example.demo.member.dao.MemberRepository;
import com.example.demo.member.service.MemberService;
import com.example.demo.member.vo.Member;
import com.example.demo.member.vo.MemberResponseDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    @GetMapping("/member/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    //회원정보 리스트
    @GetMapping(value = "/member/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

    //내정보
    @GetMapping("/member/Info") //내 정보
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


    //회원정보 수정페이지
    @GetMapping("/member/update/{id}")
    public String updateseeForm(@PathVariable Long id, Model model) {

        MemberResponseDto dto = memberService.findById(id);
        model.addAttribute("member", dto);

        return "members/memberUpdate";
    }

    //로그인 페이지
    @GetMapping("/member/login")
    public String dispLogin(Principal principals) throws Exception
    {

        return "memberAuth/signIn";
    }

    //로그인 결과
    //@PostMapping("/member/login/result")
    @GetMapping("/member/login/result")
    public String dispLoginResult(Principal principals) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        String username = ((UserDetails) principal).getUsername();

        System.out.println(username);
        System.out.println(principals.getName());
        return "home";
    }


    //로그아웃
    @GetMapping("/member/logout/result")
    public String dispLogout() {

        return "home";
    }

}
