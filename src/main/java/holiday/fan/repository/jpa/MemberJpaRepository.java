package holiday.fan.repository.jpa;

import holiday.fan.domain.members.Member;
import holiday.fan.domain.members.MemberRemove;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {

    //admin에서 회원 관리하기
//    List<Member> findAllByUserId(Pageable pageable);
    Optional<Member> findByUserId(String userId);
    Optional<Member> findByUserIdAndPassword(String userId, String password);
    Optional<Member> findByEmail(String email);
}
