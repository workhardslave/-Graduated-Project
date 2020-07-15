package com.example.demo.item.dto;

import com.example.demo.hospital.domain.Hospital;
import com.example.demo.item.domain.Item;
import lombok.*;

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
