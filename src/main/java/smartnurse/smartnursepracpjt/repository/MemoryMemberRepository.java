package smartnurse.smartnursepracpjt.repository;

import smartnurse.smartnursepracpjt.domain.Member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MemoryMemberRepository implements MemberRepository {  //MemberRepository까지 적고 옵션+엔터로 메서드 불러옴
    //⭐️🌙🍋interface로 만든 MemberRepository는 '틀'일 뿐. 구현체는 여기(클래스)다.

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))  //람다.. 루프 돌리는 것. 의미는
                //member.getName()한 것이 파람으로 넘어온 name과 같은지 파악해서 같은 경우에만 필터링 되어 찾아 반환.
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return null;
    }
}
