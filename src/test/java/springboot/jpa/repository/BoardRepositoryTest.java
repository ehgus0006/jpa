package springboot.jpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import springboot.jpa.entity.Board;
import springboot.jpa.entity.Member;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void testRead3() {
        Object result = boardRepository.getBoardByBno(100L);
        Object[] arr = (Object[]) result;

        System.out.println(arr[0]); // Board : toString
        // ((Board)arr[0]).getContent()
        System.out.println(arr[1]); // Member : toString
        System.out.println(arr[2]); // Long : toString


    }


    @Test
    public void testWithReplyCount() {
//        select * from ... order by title, by bno desc
//        select * from .. .where  ... order by bno asc
//        Sort.by("title").ascending().and(Sort.by(""bno).descending())
        Sort sort = Sort.by("bno").descending();
        Pageable pageable = PageRequest.of(0, 10, sort);
        Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageable);

        Stream<Object[]> stream =  result.get();
        stream.forEach(row -> {
            Object[] objArr = (Object[]) row;
            Board b = (Board) objArr[0];
            Member member = (Member) objArr[1];
            Long cnt = (Long) objArr[2];
            System.out.println("#########################################");
            System.out.println(b);
            System.out.println(member);
            System.out.println(cnt);
            System.out.println("#########################################");
        });

//        stream.forEach(row -> {
//            System.out.println(Arrays.toString(row));
//        });

    }

    @Transactional
    @Test
    public void testGetBoardWithReply() {

        List<Object[]> result = boardRepository.getBoardWithReply(100L);

        for (Object[] arr : result) {
            System.out.println(Arrays.toString(arr));
        }
    }

    @Test
    public void testReadWithWriter() {
        // select b, w from board b left join member m on b.email = m.email
        // where b.bno = 100;
        // Object => object[] => [{Board객체}, {Member객체}]
        Object result = boardRepository.getBoardWithWriter(100L);
        Object[] arr = (Object[]) result;
//        for(Object obj:arr) System.out.println(obj);

        System.out.println(Arrays.toString(arr));
    }

    @Transactional
    @Test
    public void testRead1() {
        Optional<Board> result = boardRepository.findById(100L);

        Board board = result.get();

        System.out.println(board);
        System.out.println(board.getWriter());
    }

    @Test
    public void insertBoard() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = new Member("user" + i + "@aaa.com");

            Board board = new Board("Title" + i, "Content" + i, member);

            boardRepository.save(board);
        });
    }

}