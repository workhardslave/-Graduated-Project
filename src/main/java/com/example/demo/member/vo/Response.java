package com.example.demo.member.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {
    private Person data;
    private Map<String,String> headers;

    public Person getData(){
        return data;
    }

    public void setData(Person data){
        this.data =data;
    }

}
