package com.example.demo.item.value;


import com.example.demo.item.domain.Item;
import org.springframework.context.annotation.Description;

import javax.persistence.Entity;

@Entity
@Description("T")
public class toy extends Item {

    private String type;    // 장난감 종류
}
