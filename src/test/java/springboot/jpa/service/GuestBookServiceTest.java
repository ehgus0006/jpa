package springboot.jpa.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import springboot.jpa.dto.GuestBookDTO;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GuestBookServiceTest {

    @Autowired
    private GuestBookService service;

    @Test
    public void testRegister(){
        GuestBookDTO guestBookDTO = new GuestBookDTO("Title", "Content", "Writer");

        System.out.println(service.register(guestBookDTO));

    }

}