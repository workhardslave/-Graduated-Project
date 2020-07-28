package com.example.demo.Item.value;

import com.example.demo.Item.domain.Item;
import org.springframework.context.annotation.Description;

import javax.persistence.Entity;

@Entity
@Description("T")
public class toy extends Item {

    private String type;    // 장난감 종류
}
