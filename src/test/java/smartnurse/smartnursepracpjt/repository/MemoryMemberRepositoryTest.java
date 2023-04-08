package smartnurse.smartnursepracpjt.repository;

//import org.junit.jupiter.api.Assertions;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import smartnurse.smartnursepracpjt.domain.Member;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    public void afterEach() {  //매 테스트 끝날때마다 이 동작 하렴(Call Back Method)
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();  //findById의 반환 타입이 Optional이기 때문에 get()으로 꺼낼 수 있음.

        System.out.println("result = " + (result == member));  //이것도 가능하지만.. 글자로 계속 볼 순 없자나여. assertion 기능 이용합시다.

        //Assertions.assertEquals(member, result);  //member가 findById했을때 튀어나오길 기대함. test 실행해보면 출력되는건 없으나 통과는 됨.

        //Assertions.assertEquals(member, null);  //이거 돌려보면 wrong test case라서 fail뜸

        assertThat(member).isEqualTo(result);  //member =?= result(Static import로 맨 앞의 Assertions. 없앰... 이게 머지 ??? ? ?? ? ?? ?? ?? => 저기 8번째줄에 import static org.assertj.core.api.Assertions.*; 이거. 이후로는 assertThat을 그냥 바로 칠 수 있게 댐.)
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member4 = new Member();
        member4.setName("spring4");
        repository.save(member4);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);

    }
}
