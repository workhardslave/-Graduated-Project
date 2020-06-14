package com.example.demo.diagnosis.controller;

import com.example.demo.diagnosis.domain.Diagnosis;
import com.example.demo.diagnosis.vo.DiagnosisDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DiagnosisApiController {

    //ai모델변경
    @PostMapping("/api/sRemodeling")
    public void variable(@RequestBody List<String> name) {

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:80/reset";

        MultiValueMap<String,String> parameters = new LinkedMultiValueMap<String,String>();
        Diagnosis diagnosis = new Diagnosis();

        //승환이가 던져주는 값
        for(int i=0;i<name.size();i++) {

        }
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,parameters,String.class);
    }


}
