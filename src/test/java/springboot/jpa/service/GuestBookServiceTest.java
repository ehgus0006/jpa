package springboot.jpa.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import springboot.jpa.dto.GuestBookDTO;
import springboot.jpa.dto.PageRequestDTO;
import springboot.jpa.dto.PageResultDTO;
import springboot.jpa.entity.GuestBook;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GuestBookServiceTest {

    @Autowired
    private GuestBookService service;

    @Test
    public void testSearch() {
        PageRequestDTO pageRequestDTO = new PageRequestDTO();
        pageRequestDTO.setType("tc");
        pageRequestDTO.setKeyword("수정");
        PageResultDTO<GuestBookDTO, GuestBook> resultDTO = service.getList(pageRequestDTO);

        for (GuestBookDTO guestBookDTO : resultDTO.getDtoList()) {
            System.out.println(guestBookDTO);
        }
        System.out.println("------------------------------------");
        System.out.println("PREV:" + resultDTO.isPrev());
        System.out.println("NEXT:" + resultDTO.isNext());
        System.out.println("TOTAL:" + resultDTO.getTotalPage());
        System.out.println("------------------------------------");


    }


    @Test
    public void testRegister(){
        GuestBookDTO guestBookDTO = new GuestBookDTO("Title", "Content", "Writer");

        System.out.println(service.register(guestBookDTO));

    }

}