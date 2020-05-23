package com.example.demo.dog.controller;

import com.example.demo.dog.dao.DogRepository;
import com.example.demo.dog.dto.DogResponseDto;
import com.example.demo.dog.dto.DogSaveRequestDto;
import com.example.demo.dog.service.DogService;
import com.example.demo.member.controller.MemberForm;
import com.example.demo.member.dao.MemberRepository;
import com.example.demo.member.vo.Member;
import com.example.demo.member.vo.MemberResponseDto;
import com.example.demo.member.vo.MemberSaveRequestDto;
import com.example.demo.overlap.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
public class DogController {

    private final DogRepository dogRepository;
    private final DogService dogService;
    private final MemberRepository memberRepository;

    // 사용자 강아지 정보 입력 홈페이지
    @GetMapping("/member/dog/singup")
    public String DogcreateForm(Model model) {
        model.addAttribute("dogForm", new DogForm());
        return "";
    }

    // 사용자 자신의 강아지 정보 조회 홈페이지
    @GetMapping("/member/dogs")
    public String DogInfo(Model model, Principal principal) {
        Member member = memberRepository.findEmailCheck(principal.getName());
        List<DogResponseDto> Dogs = dogService.findAllDesc(member);
        model.addAttribute("dog", Dogs);
        return "";
    }

    // 강아지 정보 수정 및 삭제 홈페이지
    @GetMapping("/member/dogs/settings/{id}")
    public String updateForm(@PathVariable Long id, Model model) {

        DogResponseDto dto = dogService.findById(id);
        model.addAttribute("dog", dto);

        return "";
    }

    // 관리자, 반려견 정보조회
    @GetMapping("/admin/dogs/dog_info/{id}")
    public String adminDogInfo(@PathVariable Long id, Model model, Principal principal) {
        DogResponseDto dto = dogService.findById(id);

        Member member = memberRepository.findEmailCheck(principal.getName());
        List<DogResponseDto> dogs = dogService.findAllDesc(member);
        model.addAttribute("dogs", dogs);

        return "dog/admin_dogInfo";
    }
}