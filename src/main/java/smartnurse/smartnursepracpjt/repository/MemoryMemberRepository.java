package smartnurse.smartnursepracpjt.repository;

import smartnurse.smartnursepracpjt.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {
    //MemberRepository까지 적고 옵션+엔터로 메서드 불러옴
    //⭐️🌙🍋interface로 만든 MemberRepository는 '틀'일 뿐. 구현체는 여기(클래스)다.

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);  //저장될때 id가 자동으로 셋팅되게 설정함
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
                .findAny();  //하나라도 찾으면 반환. 끝까지 돌렸는데 없으면? Optional에서 null 포함해서 반환함.
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {  //Test 돌릴때 그 순서에 구애받지 않기 위함. . . 매 테스트 끝날때마다 저장소를 비워줌
        store.clear();
    }

}
