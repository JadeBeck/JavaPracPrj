package smartnurse.smartnursepracpjt.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import smartnurse.smartnursepracpjt.domain.Member;
import smartnurse.smartnursepracpjt.repository.MemoryMemberRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

    MemberService memberService;// = new MemberService();
    MemoryMemberRepository memberRepository;// = new MemoryMemberRepository();

    @BeforeEach  //각 테스트 생성 전에 MemoryMemberRepository를 만들고 걔를 MemberService에 넣어줌(🔥🔥🔥🔥🔥????????????????)
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach  //이걸 해줘야지!! 밑에서 이름을 다 spring이라고 동일하게 써도 에러가 안터집니다.
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void join() {
        //given ➡이런 상황이 주어졌어
        Member member = new Member();
        member.setName("spring");

        //when ➡이걸 실행했을 때
        Long saveId = memberService.join(member);  //멤버서비스의 조인을 검증할거야(어떻게? member 객체 넣어서)

        //then ➡결과론 이게 나와야 해
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());  //Static Import 만들기:: assertThat에 커서 놓고 압션 + 엔터
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));  //자동완성? 압션 커맨드 V
        //assertThrows(IllegalStateException.class, () -> memberService.join(member2)) ➡️ memberService.join(member2) 로직을 태우면 IllegalStateException 에러가 터져야 함
        assertThat(e.getMessage()).isEqualTo("동명의 회원 이미 존재함");
        
        /*try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("동명의 회원 이미 존재함");
        }*/

        //then

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}