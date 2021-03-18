package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter @Setter
@DiscriminatorValue("M")//싱글테이블 dtype에 넣을값
public class Movie extends Item{

    private String director;//감독
    private String actor;//배우

}
