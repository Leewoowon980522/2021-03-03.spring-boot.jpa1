package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class Category {

    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "category_item",
            joinColumns = @JoinColumn(name = "category_id"),inverseJoinColumns = @JoinColumn(name = "item_id"))
    //다대다는 중간테이블이 있어야함 <-name
    //중간테이블에 있는 category_id <- joinColumns
    //중간테이블에 Item쪽으로 들어가는 매핑을 해줌 <- inverseJoinColumns
    private List<Item> items = new ArrayList<>();

    //=====같은 엔티티에서 매핑을해줌=====//
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();//자식은 여러개를 가질수 있음

    //=====연관관계 편의 메서드=====//
    //양방향이이어야 해서 부모,자식 컬렉션에 들어가야함//
    public void addChildCategory(Category child){
        this.child.add(child);//자식 값
        child.setParent(this);//부모 값
    }
}
