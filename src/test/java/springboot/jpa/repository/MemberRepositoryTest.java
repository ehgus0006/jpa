package springboot.jpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import springboot.jpa.entity.Member;

import java.util.function.IntConsumer;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void insertMembers() {
        // Lamda : 오직 하나의 추상메소드를 가진 인터페이스의 구현체
        MyInterface mi = new MyInterface() {

            @Override
            public void accept(int value) {
                System.out.println(value);
            }
        };
        IntStream.rangeClosed(1,100).forEach(i -> {
            Member member = new Member("user"+i+"@yju.ac.kr", "1111","User"+i);


            memberRepository.save(member);
        });
    }

    interface MyInterface extends IntConsumer {
    }

}