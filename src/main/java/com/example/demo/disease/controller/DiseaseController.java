package com.example.demo.disease.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
@Slf4j
public class DiseaseController {
    
    // 반려견 질병 정보 조회 페이지
    @GetMapping("/admin/disease/info")
    public String DiseaseInfoPage() {
        return "disease/diseaseInfo";
    }
}