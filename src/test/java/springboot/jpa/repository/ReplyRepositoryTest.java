package springboot.jpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import springboot.jpa.entity.Board;
import springboot.jpa.entity.Reply;

import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ReplyRepositoryTest {

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void readReply() {
        Optional<Reply> result = replyRepository.findById(2L);
        Reply reply = result.get();

        System.out.println(reply);
        System.out.println(reply.getBoard());
    }

    @Test
    public void insertReply() {
        IntStream.rangeClosed(1, 300).forEach(i -> {
            long bno = (long)(Math.random()*100)+1;
            Board board = new Board(bno);
            Reply reply = new Reply("Reply..." + i, board, "guest");

            replyRepository.save(reply);
        });
    }



}