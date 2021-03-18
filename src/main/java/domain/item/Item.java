package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)//부모테이블에 사용,싱글테이블 전략(한 테이블에 다떄려박음)
@DiscriminatorColumn(name = "dtype")//자식테이블을 구분하기위해 사용
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    //=====공통 속성=====//
    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")//n:m
    private List<Category> categories = new ArrayList<>();

}
