package com.example.demo.disease.controller;

import com.example.demo.collection.domain.Diagnosis;
import com.example.demo.collection.service.DiagnosisService;
import com.example.demo.collection.vo.DiagnosisDto;
import com.example.demo.disease.dto.DiseaseCountDto;
import com.example.demo.disease.service.DiseaseService;
import com.example.demo.dog.service.DogService;
import com.example.demo.dog.vo.DogResponseDto;
import com.example.demo.dog.vo.DogUpdateRequestDto;
import com.example.demo.member.controller.MemberForm;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.member.vo.Member;
import com.example.demo.member.vo.MemberResponseDto;
import com.example.demo.member.vo.MemberSaveRequestDto;
import com.example.demo.overlap.Address;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
import org.springframework.web.bind.annotation.*;
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
    private final DiagnosisService diagnosisService;

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
    public String callAPI_put(@Valid DiseaseForm form, Model model, Principal principal) throws JsonProcessingException {

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:80/test";

        MultiValueMap<String,String> parameters = new LinkedMultiValueMap<String,String>();
        Diagnosis diagnosis = new Diagnosis();



        parameters.add("증상1", form.getName1());
        parameters.add("증상2", form.getName2());
        parameters.add("증상3", form.getName3());
        parameters.add("증상4", form.getName4());
        parameters.add("증상5", form.getName5());


        //플라스크에 증상 값을 POST 매핑으로 던져준다.
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,parameters,String.class);

//        ///////////////////////////////////////////////////////////////

        HashMap<String, Object> result = new HashMap<String, Object>();

        String jsonInString = "";
        JsonParser parser = new JsonParser();


        //FLASK에서 예측값 받아오기
        try {

//            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders header = new HttpHeaders();
            HttpEntity<?> entity = new HttpEntity<>(header); //값받기

            String url2 = "http://localhost:80/test";
            ResponseEntity<Object> resultMap = restTemplate.exchange(url2, HttpMethod.POST,entity, Object.class);

            result.put("Statuscode", resultMap.getStatusCodeValue());
            result.put("header", resultMap.getHeaders());
            result.put("body", resultMap.getBody());

            ObjectMapper mapper = new ObjectMapper();

            jsonInString = mapper.writeValueAsString(resultMap.getBody());


        } catch (HttpClientErrorException | HttpServerErrorException e) {
            result.put("statusCode", e.getRawStatusCode());
            result.put("body", e.getStatusText());
            System.out.println("dfdfdfdf");
            System.out.println(e.toString());
        } catch (Exception e) {
            result.put("statusCode", "999");
            result.put("body", "excpetion오류");
            System.out.println(e.toString());
        }


        Object obj = parser.parse(jsonInString);
        JsonObject jsonObj = (JsonObject) obj;
        JsonElement k = jsonObj.get("코로나바이러스");

        Member member = memberRepository.findEmailCheck(principal.getName());
        //setting
        diagnosisService.DiagnosisSetting(jsonObj.get("data").toString(),jsonObj.get("코로나바이러스").toString(),
                jsonObj.get("마카다미아너트중독증").toString(), jsonObj.get("기관지확장증").toString(), form.getChoice(),member);


        if(member != null) {
            model.addAttribute("member", member);
            model.addAttribute("Diagnosis", jsonObj.get("data"));
            model.addAttribute("Corna", jsonObj.get("코로나바이러스"));
            model.addAttribute("Makana", jsonObj.get("마카다미아너트중독증"));
            model.addAttribute("Bronchus", jsonObj.get("기관지확장증"));
        }

        return "members/recommends/recommendation";

    }

    //회원이 보는 진단기록리스트
    @GetMapping(value = "/member/chart/record")
    public String list(Model model, Principal principal) {
        Member member = memberRepository.findEmailCheck(principal.getName());//추후 ASPECT 적용대상
        List<DiagnosisDto> diagnosis = diagnosisService.findAllDesc(member);
        model.addAttribute("dias", diagnosis);


        return "diagnosis/diagnosisList";
    }


    // 진단 시각화 페이지
    @GetMapping("/member/chart/record/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        DiagnosisDto dto  = diagnosisService.findById(id);
        model.addAttribute("member", dto);

        return "home";
    }

}