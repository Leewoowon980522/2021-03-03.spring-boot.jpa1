package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service//자동으로 spring bean으로 등록됨
@Transactional(readOnly = true)//JPA에서의 데이터변경은 트랜잭션 안에서 실행되야함, 읽기 전용,최적화
@RequiredArgsConstructor//final이 있는필드만 가지고 생성자를 만들어줌
public class MemberService {

    private final MemberRepository memberRepository;

    //=====회원 가입=====//
    @Transactional//위에서 선언한거보다 우선권이 있어 readOnly = true가 X(readOnly기본값 false)
    public Long join(Member member){
        validateDuplicateMember(member);//중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //EXCEPTION
        List<Member> findMembers = memberRepository.findByName(member.getName());//이름 찾기
        if(!findMembers.isEmpty()){//회원여부확인 이미존재한다면 findMembers에 값이있기에 if내부 문장 실행
                throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //=====회원 전체 조회=====//
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    //=====한건만 조회=====//
    public Member findOne(Long memberId){
        return  memberRepository.findOne(memberId);
    }
}
