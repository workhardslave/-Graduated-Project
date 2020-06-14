package com.example.demo.diagnosis.controller;

import com.example.demo.diagnosis.domain.Diagnosis;
import com.example.demo.diagnosis.service.DiagnosisService;
import com.example.demo.diagnosis.vo.DiagnosisDto;
import com.example.demo.diagnosis.vo.DiagnosisNameCountDto;
import com.example.demo.disease.dto.DiseaseNameCountDto;
import com.example.demo.disease.dto.DiseaseResponseDto;
import com.example.demo.disease.service.DiseaseService;
import com.example.demo.member.controller.MemberForm;
import com.example.demo.reserve.service.ReserveService;
import com.example.demo.reserve.vo.ReserveResponseDto;
import com.example.demo.symptom.repository.SymptomRepository;
import com.example.demo.symptom.vo.Symptom;
import com.example.demo.symptom.vo.SymptomForm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class DiagnosisApiController {


    private final SymptomRepository symptomRepository;
    private final DiseaseService diseaseService;
    private final ReserveService reserveService;
    private final DiagnosisService diagnosisService;
    //ai모델변경
    @PostMapping("/api/AIRemodeling")
    public String variable(@Valid SymptomForm form, Model model) {


        log.info("값확인");
        log.info("증상"+form.getModel());
        log.info("test_size"+form.getTest_size());
        log.info(form.getRandom_state());

        symptomRepository.save(Symptom.builder()
                .name(form.getModel())
                .build());


        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:80/reset";

        MultiValueMap<String,String> parameters = new LinkedMultiValueMap<String,String>();

        parameters.add("add", form.getModel());
        parameters.add("random_state", form.getRandom_state());
        parameters.add("test_size", form.getTest_size());

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,parameters,String.class);

        List<DiseaseResponseDto> diseasesAll = diseaseService.findAllDesc();

        System.out.println(diseasesAll.get(0).getName());

        List<DiseaseNameCountDto> diseaseNames = diseaseService.findNameCount();

        List<ReserveResponseDto> reservations = reserveService.findAll();

        List<DiagnosisNameCountDto> diagnosisNames = diagnosisService.findNameCount();

        model.addAttribute("dis", diseasesAll);
        model.addAttribute("disName", diseaseNames);
        model.addAttribute("reserves", reservations);
        model.addAttribute("diagName", diagnosisNames);
        model.addAttribute("symptomForm", new SymptomForm());

        return "disease/diseaseInfo";
    }


}
