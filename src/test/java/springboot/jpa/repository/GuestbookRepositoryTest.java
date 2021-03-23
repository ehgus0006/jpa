package springboot.jpa.repository;

        import com.querydsl.core.BooleanBuilder;
        import com.querydsl.core.types.dsl.BooleanExpression;
        import org.junit.jupiter.api.Test;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.context.SpringBootTest;
        import org.springframework.data.domain.Page;
        import org.springframework.data.domain.PageRequest;
        import org.springframework.data.domain.Sort;
        import springboot.jpa.entity.GuestBook;
        import springboot.jpa.entity.QGuestBook;

        import java.awt.print.Pageable;
        import java.util.Optional;
        import java.util.stream.IntStream;

        import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class GuestbookRepositoryTest {
    private static final String GuestBook = null;
    @Autowired
    private GuestbookRepository guestbookRepository;

    @Test
    public void insertDummies(){
        IntStream.rangeClosed(1, 300).forEach(i -> {
            GuestBook guestBook = new GuestBook("Title..." + i, "Content" + i,
                    "user" + (i % 10));
            GuestBook save = guestbookRepository.save(guestBook);
            System.out.println(save);
        });
    }

    @Test
    public void updateTest(){
        Optional<GuestBook> result = guestbookRepository.findById(300L);

        if(result.isPresent()){
            GuestBook guestBook = result.get();
            guestBook.changeTitle("Change Title");
            guestBook.changeContent("Change Content");

            guestbookRepository.save(guestBook);
        }
    }

    @Test
    public void testQuery(){
        PageRequest pageable = PageRequest.of(0, 10, Sort.by("gno").descending());
        // 0번 페이지에 나타낼 10개의 레코드를 주세요. 이 때 레코드들은 gno 칼럼 값을 기준으로 내림차순한다.
        QGuestBook qGuestBook = QGuestBook.guestBook; //1
        String keyword = "1";

        BooleanBuilder builder = new BooleanBuilder();//2

        //title like '%1%'
        BooleanExpression expression = qGuestBook.title.contains(keyword);
        builder.and(expression);//4

        Page<GuestBook> result = guestbookRepository.findAll(builder, pageable);

        result.stream().forEach(guestBook -> {
            System.out.println(guestBook);
        });
    }

    @Test
    public void testQuery2(){
        PageRequest pageable = PageRequest.of(0, 10, Sort.by("gno").descending());
        // 0번 페이지에 나타낼 10개의 레코드를 주세요. 이 때 레코드들은 gno 칼럼 값을 기준으로 내림차순한다.
        QGuestBook qGuestBook = QGuestBook.guestBook; //1
        String keyword = "1";

        BooleanBuilder builder = new BooleanBuilder();//2


        //title like '%1%'
        BooleanExpression exTitle = qGuestBook.title.contains(keyword);

        // content like '%1%'
        BooleanExpression exContent = qGuestBook.title.contains(keyword);

        // title like '%1%'
        BooleanExpression exAll = exTitle.or(exContent);

        builder.and(exAll);

        builder.and(qGuestBook.gno.gt(0L));

        Page<GuestBook> result = guestbookRepository.findAll(builder, pageable);

        result.stream().forEach(guestBook -> {
            System.out.println(guestBook);
        });
    }






}