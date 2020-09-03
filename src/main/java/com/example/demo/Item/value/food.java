package com.example.demo.item.value;


import com.example.demo.item.domain.Item;
import org.springframework.context.annotation.Description;

import javax.persistence.Entity;

@Entity
@Description("F")
public class food extends Item {

    private String taste;       // 맛
    private String bestBefore;  // 유통기한
}
