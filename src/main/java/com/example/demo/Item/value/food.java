package com.example.demo.Item.value;

import com.example.demo.Item.domain.Item;
import org.springframework.context.annotation.Description;

import javax.persistence.Entity;

@Entity
@Description("F")
public class food extends Item {

    private String taste;       // 맛
    private String bestBefore;  // 유통기한
}
