package smartnurse.smartnursepracpjt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import smartnurse.smartnursepracpjt.repository.MemberRepository;
import smartnurse.smartnursepracpjt.service.MemberService;

@Configuration  //이거는 직접 스프링 빈 등록해주는 과정. 이렇게 해두면 스프링이 뜰 때 @Configuration 읽고 @Bean 캐치해서 "hum Spring Bean에 등록하란거균"하고 @Bean 밑의 로직을 호출/등록해줌.
//이렇게 직접 등록하면, 스프링이 뜰 때 코드 쫙 훑으면서 memberService()랑 memberRepository() 둘 다 컨테이너에 등록하고, 등록한 애는 MemberService에 넣어줌...
public class SpringConfig {

    /*
    //return new JdbcTemplateMemberRepository(dataSource); 용
    private final DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }*/

    /*//return new JpaMemberRepository(em);용
    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }*/

    private final MemberRepository memberRepository;
    //⬆️&⬇️interface SpringDataJpaMemberRepository에서 MemberRepository를 Spring Bean에 등록했기 때문에 Injection 받을 수 있습니당.

    @Autowired  //생성자가 아래처럼 하나면 @Autowired 애노테이션 생략도 가능
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;  //이렇게.. 위에서 설명했듯 Injection 받아서
        //바로 아래에서 return new MemberService(memberRepository⬅️이렇게 등록 가능)
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);  //여기... 괄호에 빨간줄 그이면 커맨드 P 눌러봐영. 거기 뜨는거 넣어달라는 뜻임쓰.
        //위의 의미? MemberService는 memberRepository()가 필요하다고 호출함(그러면 빈에 등록된 repository를 넣어줌)
    }

   /* @Bean
    public MemberRepository memberRepository() {
        //return new MemoryMemberRepository();
        //return new JdbcMemberRepository(dataSource);
        //return new JdbcTemplateMemberRepository(dataSource);
        //return new JpaMemberRepository(em);
    }*/
}
