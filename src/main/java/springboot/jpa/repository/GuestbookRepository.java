package springboot.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import springboot.jpa.entity.GuestBook;

public interface GuestbookRepository extends JpaRepository<GuestBook,Long>,
        QuerydslPredicateExecutor<GuestBook> {

}
