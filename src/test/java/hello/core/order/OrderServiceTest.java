package hello.core.order;

import hello.core.AppConfig;
import hello.core.domain.Member;
import hello.core.domain.Order;
import hello.core.member.Grade;
import hello.core.service.MemberService;
import hello.core.service.MemberServiceImpl;
import hello.core.service.OrderService;
import hello.core.service.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

//    Chater01-문제점 : 인터페이스에 의존하는게 아닌 클라이언트 코드에 직접 구현체쪽으로 의존하고 있어 AppConfig에서 알아서 주입하도록 변경 필요
//    MemberService memberService = new MemberServiceImpl();

//    MemberService memberService = new MemberServiceImpl();
//    OrderService orderService = new OrderServiceImpl();


//    Chater01-해결책 : memberService를 AppConfig에서 주입받도록 변경 + BeforeEach(모든 테스트 작업전 수행 = 통합테스트 할때 필요 O, 개별테스트는 없어도 괜찮)
    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder(){
        //given
        long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        //when
        Order order = orderService.createOrder(memberId, "itemA", 10000);

        //then
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
