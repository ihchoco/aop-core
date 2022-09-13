package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.repository.MemberRepository;
import hello.core.repository.MemoryMemberRepository;
import hello.core.service.MemberService;
import hello.core.service.MemberServiceImpl;
import hello.core.service.OrderService;
import hello.core.service.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//스프링기반 AppConfig qusrud
@Configuration
public class AppConfig {

    //@Bean어노테이션을 주면 스프링 컨테이너 빈 저장소에 생성이 되는데 [빈 이름, 빈 객체] 형태로 저장된다.
    //[빈 이름 = 메소드 이름, 빈 객체 = 메소드 타입]
    //ex) public DiscountPolicy discountPolicy() => [discountPolicy(빈 이름), DiscountPolic(빈 객체)] 이렇게 된다
    //빈 이름은 메서드 이름을 사용하지만, 직접 부여도 가능하다 @Bean(name="memberService2")
    //빈 이름은 항상 다른 이름을 부여해야 한다. 같은 이름을 부혀앟면, 다른 빈이 무시되거나, 기존 빈을 덮어버리거나 설정에 따라 오류가 발생


    @Bean
    public DiscountPolicy discountPolicy(){ //여기서 구현체 바꿔주면 나머지는 자동으로 바뀐다
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
    @Bean
    public MemberRepository memberRepository(){ //여기서 구현체 바꿔주면 나머지는 자동으로 바뀐다
        return new MemoryMemberRepository();
    }
    @Bean
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
}

//핵심
//1. AppConfig 에는 꼭 @Configuration을 붙여줘야한다 : 싱글톤을 보장하기 위해서(스프링에서 자체적으로 Bean을 여러번 호출해도 기존에 만들어진것을 반환하도록 처리하는로직)
//2. Beana만 사용해도 스프링 빈으로 등록되지만, 싱글톤을 보장하지 않는다.
//크게 고민하지 말고 스프링 설정 정보는 항상 @Configuration을 사용하자

