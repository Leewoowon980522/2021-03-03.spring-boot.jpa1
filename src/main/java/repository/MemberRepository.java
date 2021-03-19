package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository //spring bean으로 관리
@RequiredArgsConstructor
public class MemberRepository {

    //@PersistenceContext//spring이 EntityManager를 만들어주입
    private final EntityManager em;

    //=====회원 저장=====//
    public void save(Member member){
        em.persist(member);
    }
    //=====회원 찾기=====//
    public Member findOne(Long id){
        return em.find(Member.class,id);
    }
    //=====전체 회원 조회=====//
    public List<Member> findAll(){
        return em.createQuery("select m from Member m",Member.class).getResultList();
        //jpql(쿼리문,반환타입).결과 컬렉션으로 반환
    }
    //=====회원 이름으로 조회=====//
    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name =: name",Member.class)
                .setParameter("name",name).getResultList();
    }
}
