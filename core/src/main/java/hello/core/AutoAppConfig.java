package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.springframework.context.annotation.ComponentScan.*;

@Configuration
@ComponentScan(
        excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

    //이름을 바꾸면 expected single matching bean but found 2: memoryMemberRepository,memoryMemberRepository2 에러.
    //어떤것을 주입해야 할지 모르므로
//    @Bean(name = "memoryMemberRepository")
    //반환 타입을 MemoryMemberRepository로 바꾸면 에러X
//    MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }
}
