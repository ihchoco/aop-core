package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.repository.MemberRepository;
import hello.core.repository.MemoryMemberRepository;
import hello.core.service.MemberService;
import hello.core.service.MemberServiceImpl;
import hello.core.service.OrderService;
import hello.core.service.OrderServiceImpl;

//스프링 프레임워크 없이 직접 구현한 AppConfig 방법
public class AppConfig_bak {

    public DiscountPolicy discountPolicy(){ //여기서 구현체 바꿔주면 나머지는 자동으로 바뀐다
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
    public MemberRepository memberRepository(){ //여기서 구현체 바꿔주면 나머지는 자동으로 바뀐다
        return new MemoryMemberRepository();
    }
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
}

/*
public MemberService memberService(){
    return new MemberServiceImpl(new MemoryMemberRepository());
}

public OrderService orderService(){
    return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
}
*/

/*
AppConfig는 애플리케이션의 실제 동작에 필요한 구현 객체를 생성한다.
     MemberServiceImpl
     MemoryMemberRepository
     OrderServiceImpl
     FixDiscountPolicy

AppConfig는 생성한 객체 인스턴스의 참조(레퍼런스)를 생성자를 통해서 주입(연결)해준다.
    MemberServiceImpl => MemoryMemberRepository
    OrderServiceImpl =>MemoryMemberRepository , FixDiscountPolicy
 */
