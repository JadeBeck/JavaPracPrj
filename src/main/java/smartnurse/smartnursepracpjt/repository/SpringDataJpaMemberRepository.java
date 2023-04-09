package smartnurse.smartnursepracpjt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smartnurse.smartnursepracpjt.domain.Member;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    //이렇게 인터페이스를 만들어놓고 Jpa Data가 제공하는 JpaRepository를 extends하면
    //SpringDataJpa가 인터페이스에대한 구현체를 만들고 Spring Bean에 등록해놓음.
    //이렇게 등록하면 SpringConfig에서 인젝션 받을 수 있음(DI)

    @Override
    Optional<Member> findByName(String name);  //JPQL select m from Memberver m where.mname = ?
}
