package com.example.demo.disease.controller;

import com.example.demo.disease.dto.DiseaseCountDto;
import com.example.demo.disease.service.DiseaseService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class DiseaseController {

    private final DiseaseService diseaseService;
    
    // 반려견 질병 정보 조회 페이지
    @GetMapping("/admin/disease/info")
    public String DiseaseInfoPage(Model model, DiseaseCountDto diseaseCountDto) {
        List<DiseaseCountDto> diseases = diseaseService.findCount();
        System.out.println("값확인" + diseases.get(0).getType());

//        Map<String, Integer> diseases = new TreeMap<>();
//        diseases.put("감기", 11);
//        diseases.put("바이러스", 8);
//        diseases.put("피부병", 5);
        model.addAttribute("diseases", diseases);

        return "disease/diseaseInfo";
    }
}