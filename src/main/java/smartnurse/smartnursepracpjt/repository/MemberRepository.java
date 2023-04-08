package smartnurse.smartnursepracpjt.repository;

import smartnurse.smartnursepracpjt.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    //⭐️🌙🍋지금 여기서 interface로 만든 MemberRepository는 '틀'일 뿐. 구현체는 class MemoryMemberRepository

    Member save(Member member);

    Optional<Member> findById(Long id);

    Optional<Member> findByName(String name);

    List<Member> findAll();

}
