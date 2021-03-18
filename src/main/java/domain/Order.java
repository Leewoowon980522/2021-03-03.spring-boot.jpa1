package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "orders")//Table 이름
@Getter @Setter
public class Order {
    @Id @GeneratedValue
    @Column(name = "order_id")//Column 이름
    private Long id;

    @ManyToOne(fetch = LAZY)
    //n:1,지연로딩(EAGER로 하면 연관된 테이블을 다가져오기때문에 예측하기 어려움,n+1 쿼리가 많이 나가게됨)
    //OneToOne,ManyToOne은 기본이 즉시로딩이기때문에 LAZY설정을 해줘야함
    @JoinColumn(name = "member_id")//외래키의 이름이 member_id로 됨 Member Table의 member_id
    private Member member;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    //1:n, 읽기전용 OrderItem.class의 order
    //Order를 orderItems에 넣고 Order를 저장하면 orderItems에도 저장이됨
    private List<OrderItem> orderItems;

    @OneToOne(fetch = LAZY,cascade = CascadeType.ALL)//값만 세팅해두고 Order만 persist하면 delivery도 저장이됨
    @JoinColumn(name = "delevery_id")
    private Delivery delivery;

    @Enumerated(EnumType.STRING)//enum class,EnumType.STRING로 중간에 값이 추가되어도 이상없게 만듬
    private OrderStatus status;//주문상태 [ORDER, CANCEL]

    private LocalDateTime orderDate;//주문시간

    //=====연관관계 편의 메서드=====//
    public void setMember(Member member){
        this.member = member;
        member.getOrders().add(this);
    }
    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }
}
