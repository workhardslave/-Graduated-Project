package com.example.demo.disease.controller;

import com.example.demo.disease.dto.DiseaseCountDto;
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
    @GetMapping("/admin/disease/info")
    public String DiseaseInfoPage(Model model, DiseaseCountDto diseaseCountDto, DiseaseResponseDto diseaseResponseDto) {
        List<DiseaseCountDto> diseases = diseaseService.findCount();
        System.out.println("값확인: " + diseases.get(0).getType());

//        Map<String, Integer> diseases = new TreeMap<>();
//        diseases.put("감기", 11);
//        diseases.put("바이러스", 8);
//        diseases.put("피부병", 5);

        List<DiseaseResponseDto> diseasesAll = diseaseService.findAllDesc();
        System.out.println("질병명 확인: " + diseasesAll.get(0).getName());
        System.out.println("질병증상 확인: " + diseasesAll.get(0).getSymptom());
        System.out.println("질병타입 확인: " + diseasesAll.get(0).getType());

        model.addAttribute("diseases", diseases);
        model.addAttribute("diseasesAll", diseasesAll);

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



    //flask 에게 데이터 넘겨줌
    @PostMapping("/api/disease/form")
    public String callAPI_put(@Valid DiseaseForm form) throws JsonProcessingException {

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:80/test";

        MultiValueMap<String,String> parameters = new LinkedMultiValueMap<String,String>();
        parameters.add("증상1", form.getName1());
        parameters.add("증상2", form.getName2());
        parameters.add("증상3", form.getName3());
        parameters.add("증상4", form.getName4());
        parameters.add("증상5", form.getName5());


        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,parameters,String.class);
        System.out.println("--------------------------");
        System.out.println(responseEntity);
        return "home";

    }

}