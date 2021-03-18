package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
//값 타입
//값 타입은 변경이 되면 안되기에 setter를 사용하면 X
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address(){}

    //생성자에서 값을 모두 초기화해 변경 불가능한 클래스로 만듬
    public Address(String city, String street, String zipcode){
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
