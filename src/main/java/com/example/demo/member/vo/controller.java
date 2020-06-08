package com.example.demo.member.vo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;


@RestController
public class controller {


    @GetMapping("/abc")
    public String callAPI() throws JsonProcessingException {

        HashMap<String, Object> result = new HashMap<String, Object>();

        String jsonInString = "";

        try {

            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders header = new HttpHeaders();
            HttpEntity<?> entity = new HttpEntity<>(header); //값받기

            String url = "http://localhost:80/test";

//            UriComponents uri = UriComponentsBuilder.fromHttpUrl(url+"?"+"key=430156241533f1d058c603178cc3ca0e&targetDt=20120101").build();

            ResponseEntity<Object> resultMap = restTemplate.exchange(url, HttpMethod.GET, entity, Object.class);
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

        System.out.println("확인해보기");
        System.out.println(jsonInString);
        return jsonInString;

    }

    @PostMapping("/put")
    public void callAPI_put() throws JsonProcessingException {

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:80/test";
        String name = "abc";
        String password="1234";

        MultiValueMap<String,String> parameters = new LinkedMultiValueMap<String, String>();
        parameters.add("j_username", name);
        parameters.add("j_password", password);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,parameters,String.class);
        System.out.println("--------------------------");
        System.out.println(responseEntity);

    }
}
