package com.example.demo.dog.controller;


import com.example.demo.dog.service.DogService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class DogController {

    private final DogService dogService;

    @GetMapping("/dogtest")
    public String dog(){
        return "Hello" + dogService.getName();
    }
}