package com.example.demo.dog.service;


import com.example.demo.dog.controller.DogController;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DogService {


    @Transactional
    public String getName(){
        return "TestDog";
    }
}
