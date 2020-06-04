package com.example.demo.disease.controller;

import com.example.demo.disease.dto.DiseaseResponseDto;
import com.example.demo.disease.service.DiseaseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

@Controller
@AllArgsConstructor
@Slf4j
public class DiseaseController {

    DiseaseService diseaseService;
    
    // 반려견 질병 정보 조회 페이지
    @GetMapping("/admin/disease/info")
    public String DiseaseInfoPage(Model model) {
//        List<DiseaseResponseDto> diseases = diseaseService.findAllDesc();
        Map<String, Integer> diseases = new TreeMap<>();
        diseases.put("감기", 11);
        diseases.put("바이러스", 8);
        diseases.put("피부병", 5);
        model.addAttribute("diseases", diseases);

        return "disease/diseaseInfo";
    }
}