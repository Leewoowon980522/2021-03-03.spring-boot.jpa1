package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue//기본키,값순차 증가
    @Column(name = "member_id")//Column 이름
    private Long id;

    private String name;//이름
    
    @Embedded//값타입 포함 어노테이션
    private Address address;//주소

    @OneToMany(mappedBy = "member")//1:n, mappedBy 읽기 전용 Order.class에 member
    private List<Order> orders = new ArrayList<>();
}
