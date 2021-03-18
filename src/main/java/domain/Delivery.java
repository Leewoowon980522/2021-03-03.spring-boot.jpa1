package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery",fetch = LAZY)//1:1, Order.class order 읽기전용
    private Order order;

    @Embedded//값타입 포함 어노테이션
    private Address address;

    @Enumerated(EnumType.STRING)//enum class,EnumType.STRING로 중간에 값이 추가되어도 이상없게 만듬
    private DeliveryStatus status;//배송상태 [READY, COMP]
}
