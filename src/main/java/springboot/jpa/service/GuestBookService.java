package springboot.jpa.service;


import springboot.jpa.dto.GuestBookDTO;
import springboot.jpa.dto.PageRequestDTO;
import springboot.jpa.dto.PageResultDTO;
import springboot.jpa.entity.GuestBook;

public interface GuestBookService {
     Long register(GuestBookDTO dto);

     default GuestBook convertDTO2Entity(GuestBookDTO dto) {
          GuestBook entity = new GuestBook(dto.getTitle(), dto.getContent(), dto.getWriter());
          return entity;
     }

     default GuestBookDTO convertEntity2DTO(GuestBook entity) {
          GuestBookDTO dto = new GuestBookDTO(entity.getGno(), entity.getTitle(),
                  entity.getContent(), entity.getWriter(), entity.getRegDate(), entity.getModDate());
          return dto;
     }

     public PageResultDTO<GuestBookDTO, GuestBook> getList(PageRequestDTO requestDTO);

     void remove(Long gno);

     void modify(GuestBookDTO dto);

     GuestBookDTO read(Long gno);

}
