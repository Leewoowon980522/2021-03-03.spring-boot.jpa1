package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    //=====상품저장=====//
    public void save(Item item){
        //처음 저장할때는 id가 없음
        if(item.getId() == null){
            em.persist(item);//상품 저장(신규등록)
        }else{
            em.merge(item);//상품 update
        }
    }
    //=====상품 하나 조회=====//
    public Item findOne(Long id){
        return em.find(Item.class,id);
    }
    //=====모든 상품 조회=====//
    public List<Item> findAll(){
        return em.createQuery("select i from Item i",Item.class).getResultList();
    }
}
