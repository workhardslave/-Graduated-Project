package com.example.demo.Item;


import com.example.demo.hospital.vo.Hospital;
import com.example.demo.overlap.BaseTimeEntity;
import com.example.demo.overlap.NotEnoughStockException;

import javax.persistence.*;
import static javax.persistence.FetchType.LAZY;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "Ftype")
public abstract class Item extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    protected String Description;

//    @ManyToOne(fetch = LAZY)
//    @Column(name = "hospital_id")
//    private Hospital hospital;

    //==비즈니스 로직==//
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

   // 수량이 부족할 경우
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }


}
