package com.example.demo.reserve.controller;

import com.example.demo.diagnosis.service.DiagnosisService;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.member.vo.Member;
import com.example.demo.reserve.service.ReserveService;
import com.example.demo.reserve.vo.ReserveResponseDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ReserveController {

    private final ReserveService reserveService;
    private final MemberRepository memberRepository;
    private final DiagnosisService diagnosisService;


    // 사용자 자신의 예약 정보 조회 홈페이지
    @GetMapping("/member/reservesInfo")
    public String ReserveInfo(Model model, Principal principal) {                   // principle: session DB에 저장되어 있는 값 가져옴
        Member member = memberRepository.findEmailCheck(principal.getName());
        List<ReserveResponseDto> Reserves = reserveService.findAllDesc(member);

        model.addAttribute("reserves", Reserves);
        return "members/reserves/reserveInfo";
    }

    // 사용자 병원 예약 수정 및 삭제 홈페이지
    @GetMapping(value = "/reserves/settings/{id}")
    public String updateForm(@PathVariable Long id, Model model) {

        ReserveResponseDto dto = reserveService.findById(id);
        model.addAttribute("reserve", dto);
        log.info(dto.getDate());

        return "members/reserves/reserveModify";
    }




    // 관리자 -> 사용자 예약 정보 조회 홈페이지
    @GetMapping("/admin/reserves")
    public String ReserveInfoAdmin(Model model) {                   // principle: session DB에 저장되어 있는 값 가져옴
        List<ReserveResponseDto> Reserves = reserveService.findAll(); //모든예약정보확인
        model.addAttribute("reserves", Reserves);

        return "admin/reserves/reserveInfoAdmin";
    }


    // 관리자 -> 사용자 병원 예약 수정 및 삭제 홈페이지
    @GetMapping(value = "/admin/reserves/settings/{id}")
    public String updateFormAdmin(@PathVariable Long id, Model model) {

        ReserveResponseDto dto = reserveService.findById(id);
        model.addAttribute("reserve", dto);

        return "admin/reserves/reserveModifyAdmin";
    }



//    // 병원 추천 페이지
//    @GetMapping("/member/recommendation")
//    public String recommendation(Model model, Principal principal) throws JsonProcessingException {
//
//        HashMap<String, Object> result = new HashMap<String, Object>();
//
//        String jsonInString = "";
//        JsonParser parser = new JsonParser();
//
//
//        //FLASK에서 예측값 받아오기
//        try {
//
//            RestTemplate restTemplate = new RestTemplate();
//
//            HttpHeaders header = new HttpHeaders();
//            HttpEntity<?> entity = new HttpEntity<>(header); //값받기
//
//            String url = "http://localhost:80/test";
//            ResponseEntity<Object> resultMap = restTemplate.exchange(url, HttpMethod.POST,entity, Object.class);
//
//            result.put("Statuscode", resultMap.getStatusCodeValue());
//            result.put("header", resultMap.getHeaders());
//            result.put("body", resultMap.getBody());
//
//            ObjectMapper mapper = new ObjectMapper();
//
//            jsonInString = mapper.writeValueAsString(resultMap.getBody());
//
//
//        } catch (HttpClientErrorException | HttpServerErrorException e) {
//            result.put("statusCode", e.getRawStatusCode());
//            result.put("body", e.getStatusText());
//            System.out.println("dfdfdfdf");
//            System.out.println(e.toString());
//        } catch (Exception e) {
//            result.put("statusCode", "999");
//            result.put("body", "excpetion오류");
//            System.out.println(e.toString());
//        }
//
//
//        Object obj = parser.parse(jsonInString);
//        JsonObject jsonObj = (JsonObject) obj;
//        JsonElement k = jsonObj.get("코로나바이러스");
//
//        Member member = memberRepository.findEmailCheck(principal.getName());
//        //setting
//        diagnosisService.DiagnosisSetting(jsonObj.get("data").toString(),jsonObj.get("코로나바이러스").toString(),
//                jsonObj.get("마카다미아너트중독증").toString(), jsonObj.get("기관지확장증").toString(), member);
//
//
//        if(member != null) {
//            model.addAttribute("member", member);
//            model.addAttribute("Diagnosis", jsonObj.get("data"));
//            model.addAttribute("Corna", jsonObj.get("코로나바이러스"));
//            model.addAttribute("Makana", jsonObj.get("마카다미아너트중독증"));
//            model.addAttribute("Bronchus", jsonObj.get("기관지확장증"));
//        }
//
//
//        return "members/recommends/recommendation";
//    }

}
