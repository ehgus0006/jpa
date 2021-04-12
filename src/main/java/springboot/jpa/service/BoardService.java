package springboot.jpa.service;

import springboot.jpa.dto.BoardDTO;
import springboot.jpa.dto.GuestBookDTO;
import springboot.jpa.entity.Board;
import springboot.jpa.entity.GuestBook;
import springboot.jpa.entity.Member;

public interface BoardService {

    Long register(BoardDTO dto);

    default Board convertDTO2Entity(BoardDTO dto) {
        Member member = new Member(dto.getWriterEmail());
        Board entity = new Board(dto.getTitle(), dto.getContent(),member);
        return entity;
    }
}
