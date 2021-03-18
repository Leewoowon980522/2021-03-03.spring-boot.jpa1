package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter @Setter
@DiscriminatorValue("A")//싱글테이블 dtype에 넣을값
public class Album extends Item{

    private String artist;
    private String etc;//기타정보

}
