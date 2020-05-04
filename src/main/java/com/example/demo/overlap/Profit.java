package com.example.demo.overlap;

import com.example.demo.hospital.vo.Hospital;
import com.example.demo.order.vo.Order;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;


//병원이익
@Getter
@Entity
@Setter
@EntityListeners(AuditingEntityListener.class) //BaseTimeEntity클래스에 Auditing 기능을 포함시킨다.
public class Profit {

    @Id @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private double profit_tax; //수수료
    private double profit_total; //수익

    @OneToOne
    private Hospital hospital;



    @LastModifiedDate//조회한 엔티티의 값이 변경할때 자동으로 시간을 부여함.
    private LocalDateTime modifiedDate;


}
