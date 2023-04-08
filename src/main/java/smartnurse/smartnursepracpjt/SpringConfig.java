package smartnurse.smartnursepracpjt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import smartnurse.smartnursepracpjt.repository.MemberRepository;
import smartnurse.smartnursepracpjt.repository.MemoryMemberRepository;
import smartnurse.smartnursepracpjt.service.MemberService;

@Configuration  //이거는 직접 스프링 빈 등록해주는 과정. 이렇게 해두면 스프링이 뜰 때 @Configuration 읽고 @Bean 캐치해서 "hum Spring Bean에 등록하란거균"하고 @Bean 밑의 로직을 호출/등록해줌.
//이렇게 직접 등록하면, 스프링이 뜰 때 코드 쫙 훑으면서 memberService()랑 memberRepository() 둘 다 컨테이너에 등록하고, 등록한 애는 MemberService에 넣어줌...
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());  //여기... 괄호에 빨간줄 그이면 커맨 P 눌러봐영. 거기 뜨는거 넣어달라는 뜻임쓰.
        //위의 의미? MemberService는 memberRepository()가 필요하다고 호출함(그러면 빈에 등록된 repository를 넣어줌)
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
