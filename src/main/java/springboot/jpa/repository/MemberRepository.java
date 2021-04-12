package springboot.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.jpa.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
