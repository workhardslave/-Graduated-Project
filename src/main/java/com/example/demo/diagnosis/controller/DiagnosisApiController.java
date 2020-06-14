package com.example.demo.diagnosis.controller;

import com.example.demo.diagnosis.domain.Diagnosis;
import com.example.demo.diagnosis.vo.DiagnosisDto;
import com.example.demo.member.controller.MemberForm;
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
    //ai모델변경
    @PostMapping("/api/AIRemodeling")
    public String variable(@Valid SymptomForm form) {


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

        return "disease/diseaseInfo";
    }


}
