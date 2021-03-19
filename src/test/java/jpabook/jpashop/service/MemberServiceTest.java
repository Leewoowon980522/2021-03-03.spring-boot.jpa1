package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;
//회원가입 테스트
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional//데이터변경하기위해,rollback해버림
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    @Rollback(false)//위에 @Transactional이 Rollback해버리기때문에 DB에 값들어갔는지확인하려면 Rollback을 false
    public void 회원가입() throws Exception{
        //given 값세팅
        Member member = new Member();
        member.setName("Lee");

        //when 조건
        Long savedId = memberService.join(member);//Id를 return받음

        //then 결과
        assertEquals(member,memberRepository.findOne(savedId));//member와memberRepository.findOne(savedId)가 같은지 확인
    }

    @Test(expected = IllegalStateException.class)//아래에서 예외가 터져서 나간얘가 IllegalStateException면 된다.
    public void 중복_회원_예외() throws Exception{
        //given
        Member member1 = new Member();
        member1.setName("Lee1");

        Member member2 = new Member();
        member2.setName("Lee1");

        //when
        memberService.join(member1);
        memberService.join(member2);


        //then
        fail("예외가 발생해야 한다.");//여기로 오면 안됨. 중복체크 실패
    }
}