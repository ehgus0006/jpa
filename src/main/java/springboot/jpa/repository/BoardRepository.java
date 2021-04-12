package springboot.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import springboot.jpa.entity.Board;


import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    // from절에는 table 이름이 아닌 클래스 이름을 적어야 하고
    // N:1 관계에서 N쪽이 left join의 왼쪽에 위치할 때는 조인 조건 명시 하지 않는다
    // 두가지 객체를 다쓸때는 앞에 Object로 명시한다
    // 특정 게시글에 대해 그 게시글의 정보와 그 게시글을 작성한 회원정보를 모두 인출
    @Query("select b, w from Board b left join b.writer w where b.bno=:bno")
    Object getBoardWithWriter(@Param("bno") Long bno);

    // 특정 게시글에 대해 그 게시글의 정보와 그 게시글의 모든 댓글 정보를 인출
    // Board : Reply = 1 : N 즉 두 테이블 간의 연결 정보는 N쪽인 Reply에 있다.
    // Board쪽에서 Reply로 연결되는 정보가 없기 때문에 명시적으로 조인 조건을 적어 줘야 한다.
    // 조인 조건에 컬럼이름을 적어주는 것이 아니고, 멤버 변수 이름을 적어준다.
    // [{Board 객체}, {Reply 객체}]의 배열을 원소로 가지는 리스트가 반환된다.
    // 반환되는 리스트의 원소 수는 그 게시글의 댓글 수와 같다.
    @Query("select b, r from Board b left join Reply r on r.board = b where b.bno=:bno")
    List<Object[]> getBoardWithReply(@Param("bno") Long bno);


    /*
     * 목록 화면에 필요한 JPQL (Java Persistence Query Language) 만들기
     * 게시글(Board) : 게시글 번호, 제목, 작성시간
     * 작성자(Member) : 회원 이름/ 이메일
     * 댓글(Reply) : 이 게시글의 댓글 수
     * */

    /*
     * Pagination 고려해야 함
     * 몇 번째 page, 한 페이지당 size, 목록 정렬 방법 =>
     * 이런 정보를 담고 있는 Pageable 객체를 repository 메소드 인자로 전달해야 함
     * Page객체가 결과 레코드들에 대응되는 Entity 객체들만 가지는 것이 아니라
     * Pagination에 필요한 정보도 포함하고 있다.
     * total page 정보도 포함되어 있다.
     * */

    @Query(value = "select b,w, count(r) from Board b left join b.writer w left join Reply r on r.board=b group by b",
            countQuery = "select count(b) from Board b")
    Page<Object[]> getBoardWithReplyCount(Pageable pageable);

    /*
    조회 화면에 필요한 JPQL만들기

    게시글 제목, 내용, 작성일자, 작성자 이름/이메일, 댓글수
    ajax로 댓글 내용보기는 구현 예정, 다음장에서
    * */
    @Query("select b,w, count(r) from Board b left join b.writer w " +
            "left join Reply r on r.board = b where b.bno= :bno ")
    Object getBoardByBno(@Param("bno") Long bno);



}
