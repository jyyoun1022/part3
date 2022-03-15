package part2.part2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import part2.part2.entity.Member;

public interface MemberRepository extends JpaRepository<Member,String> {
}
