package com.example.demo.item.value;


import com.example.demo.item.item;
import org.springframework.context.annotation.Description;

import javax.persistence.Entity;

@Entity
@Description("T")
public class toy extends item {

    private String type;    // 장난감 종류
}
