package hello.core;

import hello.core.discount.FixDiscountPolicy;
import hello.core.repository.MemoryMemberRepository;
import hello.core.service.MemberService;
import hello.core.service.MemberServiceImpl;
import hello.core.service.OrderService;
import hello.core.service.OrderServiceImpl;

public class AppConfig {
    public MemberService memberService(){
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService(){
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }

    //> 참고: 지금은 각 클래스에 생성자가 없어서 컴파일 오류가 발생한다. 바로 다음에 코드에서 생성자를 만든다.
}

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
