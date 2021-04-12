package springboot.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.jpa.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

}
