package com.example.demo.order.domain;


import com.example.demo.Item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Setter
@Getter
public class OrderItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = LAZY)
    private Item product;

    @ManyToOne(fetch = LAZY)
    private Order order;

    private int orderPrice; //주문 가격

    private int count;

    //==생성 메서드==//
    public static OrderItem createOrderItem(Item product, int orderPrice, int
            count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);
        product.removeStock(count);
        return orderItem;
    }



    //주문취소
    public void cancel() {
        getProduct().addStock(count); //취소한 수량만큼 수량 +
    }


    //==조회 로직==//
    /** 주문상품 전체 가격 조회 */
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }


}
