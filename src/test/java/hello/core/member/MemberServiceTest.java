package hello.core.member;

import hello.core.AppConfig;
import hello.core.domain.Member;
import hello.core.service.MemberService;
import hello.core.service.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

//    Chater01-문제점 : 인터페이스에 의존하는게 아닌 클라이언트 코드에 직접 구현체쪽으로 의존하고 있어 AppConfig에서 알아서 주입하도록 변경 필요
//    MemberService memberService = new MemberServiceImpl();

//    Chater01-해결책 : memberService를 AppConfig에서 주입받도록 변경 + BeforeEach(모든 테스트 작업전 수행 = 통합테스트 할때 필요 O, 개별테스트는 없어도 괜찮)
    MemberService memberService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join(){
        //given
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
