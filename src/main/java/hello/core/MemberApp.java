package hello.core;

import hello.core.member.Grade;
import hello.core.domain.Member;
import hello.core.service.MemberService;
import hello.core.service.MemberServiceImpl;

public class MemberApp {

    public static void main(String[] args) {

        //인터페이스에만 의존하도록 코드 변경(chapter01-잘못된 코드)
        //MemberService memberService = new MemberServiceImpl();

        //AppConfig에서 만들어진 memberService를 가져와서 주입시켜준다.(이전에는 구현체를 직접 클라이언트 코드에 입력하였지만 지금은 AppConfig에서 알아서 필요한걸로 셋팅해서 주입하는걸로 바꾸었음)
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();


        Member member = new Member(1L, "memberA", Grade.VIP);


        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new Member = "+member.getName());
        System.out.println("find Member = "+findMember.getName());

        //애플리케이션 로직으로 이렇게 테스트 하는 것은 좋은 방법이 아니다. JUnit 테스트를 사용하자.
    }
}
