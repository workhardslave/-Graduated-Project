package com.example.demo.Item.domain;

import com.example.demo.hospital.domain.Hospital;
import com.example.demo.member.domain.BaseTimeEntity;
import com.example.demo.item.overlap.NotEnoughAmountException;
import lombok.Builder;
import lombok.NoArgsConstructor;

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
    private int amount;

    protected String description;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    public Long getId() { return this.id = id; }

    public String getName() {
        return this.name = name;
    }

    public int getPrice() {
        return this.price = price;
    }

    public int getAmount() {
        return this.amount = amount;
    }

    public String getDescription() {
        return this.description = description;
    }

    public Hospital getHospital() {
        return this.hospital = hospital;
    }

    //==비즈니스 로직==//
    public void addAmount(int amount) {
        this.amount += amount;
    }

    // 수량이 부족할 경우
    public void removeAmount(int quantity) {
        int restAmount = this.amount - quantity;
        if (restAmount < 0) {
            throw new NotEnoughAmountException("There is no more amount");
        }
        this.amount = restAmount;
    }
}
