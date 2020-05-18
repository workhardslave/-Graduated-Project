package com.example.demo.dog.controller;

import com.example.demo.dog.dao.DogRepository;
import com.example.demo.dog.dto.DogSaveRequestDto;
import com.example.demo.dog.service.DogService;
import com.example.demo.member.controller.MemberForm;
import com.example.demo.member.dao.MemberRepository;
import com.example.demo.member.vo.Member;
import com.example.demo.member.vo.MemberSaveRequestDto;
import com.example.demo.overlap.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;


@Controller
@RequiredArgsConstructor
public class DogController {


    private final DogRepository dogRepository;
    private final DogService dogService;
    private final MemberRepository memberRepository;

    // 강아지 정보 입력 홈페이지
    @GetMapping("/member/dog/singup")
    public String DogcreateForm(Model model) {
        model.addAttribute("dogForm", new DogForm());
        return "";
    }

}



