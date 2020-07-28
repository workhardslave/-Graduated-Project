package com.example.demo.Item.dto;

import com.example.demo.Item.domain.Item;
import com.example.demo.hospital.domain.Hospital;

import lombok.Getter;

@Getter
public class ItemResponseDto {

    private Long id;
    private String name;
    private int price;
    private int amount;
    protected String description;
    private Hospital hospital;

    public ItemResponseDto(Item entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.price = entity.getPrice();
        this.amount = entity.getAmount();
        this.description = entity.getDescription();
        this.hospital = entity.getHospital();
    }
}
