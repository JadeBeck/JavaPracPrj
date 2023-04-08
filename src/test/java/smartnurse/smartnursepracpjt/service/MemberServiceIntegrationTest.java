package smartnurse.smartnursepracpjt.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import smartnurse.smartnursepracpjt.domain.Member;
import smartnurse.smartnursepracpjt.repository.MemberRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional  //💓DB는 트랜잭션 하는거 알져 ? ㅎ 이 @Transactional을 Test Case에 달면, 롤빽을 해줘버려~! 글서 DB 데이터가 다 반영 안되고 깔끔하게 지워져버림ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ.....ㅎr 것도 모르고 주석 안하고 돌리다가 왜 디비에 안쌓이는지 영문을 모르겠어서 삽질을 도대체 몇분을 했는지..... ㅎ.....
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;   //구현체는 Config 한데서 올라올 것
    @Autowired MemberRepository memberRepository;

    @Test
    void 회원_가입() {
        //given ➡이런 상황이 주어졌어
        Member member = new Member();
        member.setName("Renna");

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
}