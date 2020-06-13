package com.example.demo.disease.controller;

import com.example.demo.disease.dto.DiseaseCountDto;
import com.example.demo.disease.dto.DiseaseNameCountDto;
import com.example.demo.disease.dto.DiseaseResponseDto;
import com.example.demo.disease.service.DiseaseService;
import com.example.demo.dog.service.DogService;
import com.example.demo.dog.vo.DogResponseDto;
import com.example.demo.dog.vo.DogUpdateRequestDto;
import com.example.demo.member.controller.MemberForm;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.member.vo.Member;
import com.example.demo.member.vo.MemberSaveRequestDto;
import com.example.demo.overlap.Address;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.security.Principal;
import java.util.*;


@RequiredArgsConstructor
@Slf4j
@Controller
public class DiseaseController {

    private final DiseaseService diseaseService;
    private final MemberRepository memberRepository;
    private final DogService dogService;

    // 반려견 질병 정보 조회 페이지
    // 질병 정보 시각화
    @GetMapping("/admin/disease/info")
    public String DiseaseInfoPage(Model model) {
        List<DiseaseCountDto> diseases = diseaseService.findCount();
        System.out.println("값확인: " + diseases.get(0).getType());

        List<DiseaseResponseDto> diseasesAll = diseaseService.findAllDesc();
        System.out.println(diseasesAll.get(0).getName());

        List<DiseaseNameCountDto> diseaseNames = diseaseService.findNameCount();

        model.addAttribute("diseases", diseases);
        model.addAttribute("dis", diseasesAll);
        model.addAttribute("disName", diseaseNames);

        return "disease/diseaseInfo";
    }

    // 진료차트 홈페이지
    @GetMapping("/member/disease/chart")
    public String DiseaseForm(Model model, Principal principal) {
        Member member = memberRepository.findEmailCheck(principal.getName());//추후 ASPECT 적용대상
        List<DogResponseDto> Dogs = dogService.findAllDesc(member);

        model.addAttribute("dogs", Dogs);
        model.addAttribute("DiseaseForm", new DiseaseForm());

        return "disease/diseaseChart";
    }

    //외부 API와 연동
    @PostMapping("/api/disease/form")
    public String callAPI_put(@Valid DiseaseForm form) throws JsonProcessingException {
//        System.out.println("너는 어떤강아지니?------------------------"+form.getChoice());
//        System.out.println("너는 어케 나오니--------------------------"+form.getSymptom());
//        System.out.println("너는 크기가 몇이니?---------------"+form.getSymptom().size());
//        System.out.println("get으로 꺼내보자--------------------"+form.getSymptom().get(0));
//        System.out.println("get으로 꺼내보자--------------------"+form.getSymptom().get(1));
//        System.out.println("get으로 꺼내보자--------------------"+form.getSymptom().get(2));
//        System.out.println("get으로 꺼내보자--------------------"+form.getSymptom().get(3));
//        System.out.println("get으로 꺼내보자--------------------"+form.getSymptom().get(4));
        String jsonInString = "";
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:80/test";

        MultiValueMap<String,String> parameters = new LinkedMultiValueMap<String,String>();

        for(int i=0;i<form.getSymptom().size();i++) {
            parameters.add("증상"+i, form.getSymptom().get(i));
        }

        //플라스크에 증상 값을 POST 매핑으로 던져준다.
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,parameters,String.class);

        return "redirect:/member/recommendation";

    }
}