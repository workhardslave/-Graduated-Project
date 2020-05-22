package com.example.demo.dog.controller;

import com.example.demo.dog.dao.DogRepository;
import com.example.demo.dog.dto.DogResponseDto;
import com.example.demo.dog.dto.DogSaveRequestDto;
import com.example.demo.dog.service.DogService;
import com.example.demo.member.dao.MemberRepository;
import com.example.demo.member.vo.Member;
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
public class DogController {


    private final DogRepository dogRepository;
    private final DogService dogService;
    private final MemberRepository memberRepository;

    // 사용자 강아지 정보 입력 홈페이지
    @GetMapping("/member/dog/save")
    public String DogcreateForm(Model model) {
        model.addAttribute("flag", true);
        model.addAttribute("dogForm", new DogForm());
        return "members/dogs/dogSignUp";
    }

    // 강아지 정보 저장 API
    @PostMapping(value = "/api/member/dog/save")
    public String Dogcreate(@Valid DogForm form, BindingResult result, Principal principal, Model model) {
        if (result.hasErrors()) {
            return "members/dogs/dogSignUp";
        }


        Member member = memberRepository.findEmailCheck(principal.getName());

        DogSaveRequestDto dog = new DogSaveRequestDto();
        log.info("나이"+form.getAge()); //null
        log.info("생일"+form.getBirth());
        log.info("이름"+form.getName());
        log.info("성별"+form.getGender());
        log.info("종"+form.getValue()); //null
        log.info("멤버" + member.getName());

        dog.setAge(form.getAge());
        dog.setBirth(form.getBirth());
        dog.setGender(form.getGender());
        dog.setName(form.getName());
        dog.setValue(form.getValue());
        dog.setMember(member);
        dogService.dog_SignUp(dog);
        return "members/dogs/dogSignUp";
    }


    // 사용자 자신의 강아지 정보 조회 홈페이지
    @GetMapping("/member/dogs")
    public String DogInfo(Model model, Principal principal) {
        Member member = memberRepository.findEmailCheck(principal.getName());
        List<DogResponseDto> Dogs = dogService.findAllDesc(member);

        model.addAttribute("dogs", Dogs);
        return "members/dogs/dogInfo";
    }


    // 강아지 정보 수정 및 삭제 홈페이지
    @GetMapping("/member/dogs/settings/{id}")
    public String updateForm(@PathVariable Long id, Model model) {

        DogResponseDto dto = dogService.findById(id);
        model.addAttribute("dog", dto);

        return "members/dogs/dogSettings";
    }


//
//    //관리자 -> 멤버별 회원정보 강아지리스트 확인 홈페이지
//    @GetMapping(value = "/admin/members")
//    public String list(Model model) {
//        List<MemberResponseDto> members = memberService.findAllDesc();
//        model.addAttribute("members", members);
//        return "admin/memberList";
//    }




}



