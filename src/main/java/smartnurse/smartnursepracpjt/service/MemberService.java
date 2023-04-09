package smartnurse.smartnursepracpjt.service;

import org.springframework.transaction.annotation.Transactional;
import smartnurse.smartnursepracpjt.domain.Member;
import smartnurse.smartnursepracpjt.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

@Transactional
//@Service  //상황 시뮬.. 1)스프링 컨테이너가 뜬다 ➡ 2)코드 쫙 훑는데 @Service가 보인다 ➡ 3)"오호랑 써비쓰쟈나? 일단 컨테이너 등록 ㄱㄱ" ➡ 4)"밑에 생성자(private final~) 호출하쟝. 근데 @Autowired가 있쟈나?!" ➡ 5)"구로면,, 아핫 코드 보니까 MemberRepository가 필요하구남" ➡ 6)스프링 컨테이너에 들어있는 MemberRepository를 빼서 촥 넣어줌~!(지금 구현체는 MemoryMemberRepository니까 얘를 넣어줌)(이게 DI)
public class MemberService {  //테스트 쉽게 만들기? 커맨드 쉬프트 t

    //@Autowired
    private final MemberRepository memberRepository;  //원래는 private final MemberRepository memberRepository = new MemoryMemberRepository(); 였음
    //근데 위처럼 줄이고 거기서 커맨드 N으로 Constructor Generate함

    public MemberService(MemberRepository memberRepository) {  //MemberService가 memberRepository를 직접 생성 ㄴㄴ 외부에서 넣어주도록 고침 (Dependency Injection)
        this.memberRepository = memberRepository;
    }


    /**
     * 회원가입
     */
    public Long join(Member member) {  //🔥🔥🔥🔥🔥근데 . . name(body)을 어디서 받아오는데....
        /*//동명의 회원은 불가
        Optional<Member> result = memberRepository.findByName(member.getName());//커맨드+압션+V 하면 리턴 자동 생성 가능 ㅎ ... 근데여 Optional<Member> 이런거 별루 권장 안한대여(일단 별로 안예ㅃ..응?) 그거 말구 밑에 새로 짠 로직 ㄱㄱ
        result.ifPresent(m -> {  //m(즉 member)가 찾아지면(null이 아니라면)(즉 Optional이라 이거 쓸 수 있음)!! 아래의 로직이 동작한다. Optional을 씌우면 그 안에 멤버 객체가 존재함. optional이 없을땐.. 그냥 ifNull 이런식으로 코딩했으나, 요즘은 null일 가능성이 있을땐 Optional을 씌우면 된다.
            throw new IllegalStateException("동명의 회원 이미 존재함");
        });*/

            //동명의 회원은 불가
            validateDupllicatedName(member);
            /* 원래 아래처럼 코드가 줄줄이 있었음. 글서 이걸 method로 아랫부분에다가 따로 선언하고 그걸 불러다 씀.
             memberRepository.findByName(member.getName())  //findByName 해바. 근데 이 메쏘드 자체가 이미 Optional로 생성됐거덩? 그니까 그냥 여기다가 바로 ifPresent 써부려
                .ifPresent(m -> {
                    throw new IllegalStateException("동명의 회원 이미 존재함");
                });*/
            memberRepository.save(member);
            return member.getId();
    }

    private void validateDupllicatedName(Member member) {
        //동명의 회원은 불가
        memberRepository.findByName(member.getName())  //findByName 해바. 근데 이 메쏘드 자체가 이미 Optional로 생성됐거덩? 그니까 그냥 여기다가 바로 ifPresent 써부려
                .ifPresent(m -> {
                    throw new IllegalStateException("동명의 회원 이미 존재함");
                });
    }

    /**
     * 전체 회원 조회긔
     */
    public List<Member> findMembers() {
            return memberRepository.findAll();  //findAll 만들때 list로 만들었슘. 글서 걍 단순 return 함 댐
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}