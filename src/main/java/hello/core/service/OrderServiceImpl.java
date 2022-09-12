package hello.core.service;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.domain.Member;
import hello.core.domain.Order;
import hello.core.repository.MemberRepository;
import hello.core.repository.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    //인터페이스에만 의존하도록 코드 변경(chapter01-잘못된 코드)
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); //원래는 FixDiscountPolicy가 오든 RateDiscountPolicy가 오든 상관없이 인터페이스(DiscountPolicy)에 해당하는 부분만 자동으로 주입되게 만들어줘야한다. chapter01 잘못된코드 : OCP, DIP 위반

    //문제점 : 인터페이스에만 의존하도록 설계와 코드를 변경했다 -> 그런데 구현체가 없는데 어떻게 코드를 실행할 수 있을까? -> 실제 실행을 해보면 NPE(null pointer exception)가 발생한다.
    //해결방안 : 이 문제를 해결하려면 누군가가 클라이언트인 OrderServiceImpl 에 DiscountPolicy 의 구현 객체를 대신 생성하고 주입해주어야 한다.

    //구현체에 직접 의존하지 않고 인터페이스에만 의존하는 주입방식
    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    private MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    public OrderServiceImpl(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
