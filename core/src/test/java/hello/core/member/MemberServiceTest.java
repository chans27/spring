package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

//    MemberService memberService = new MemberServiceImpl(); 의존 관계가 인터페이스 뿐만 아니라 구현까지 모두 의존
    MemberService memberService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }


    @Test
    void join() {
        //given
        Member member = new Member(1L, "memberA", Grade.VIP);

        //then
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //when

        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
