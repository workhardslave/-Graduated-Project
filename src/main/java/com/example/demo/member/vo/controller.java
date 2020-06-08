package com.example.demo.member.vo;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class controller {

    @Autowired
    RestTemplateTestService restTemplateTestService;

    @PostMapping(value="/please")
    public void test(){
        restTemplateTestService.callPostExternalServer();
    }
}
