package com.example.demo.member.vo;

import lombok.Getter;
import lombok.Setter;

import java.awt.event.WindowFocusListener;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Person {

    private String name;
    private int age;
    public Map<String, String> info = new HashMap<>();


}
