package springboot.jpa.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import springboot.jpa.dto.GuestBookDTO;
import springboot.jpa.dto.PageRequestDTO;
import springboot.jpa.dto.PageResultDTO;
import springboot.jpa.entity.GuestBook;
import springboot.jpa.repository.GuestbookRepository;

import java.util.Optional;
import java.util.function.Function;

@Service
@Slf4j
public class GuestBookServiceImpl implements GuestBookService{

    @Autowired
    private GuestbookRepository guestbookRepository;

    @Override
    public Long register(GuestBookDTO dto) {
        log.debug("service 시작");

        GuestBook entity = convertDTO2Entity(dto);

        log.debug("entity:"+entity);
        GuestBook guestBook = guestbookRepository.save(entity);
        return guestBook.getGno();
    }

    @Override
    public PageResultDTO<GuestBookDTO, GuestBook> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("gno").descending());

        Page<GuestBook> result = guestbookRepository.findAll(pageable);

        Function<GuestBook, GuestBookDTO> fn = (entity -> convertEntity2DTO(entity));


        return new PageResultDTO<GuestBookDTO, GuestBook>(result,fn);
    }

    @Override
    public void remove(Long gno) {

    }

    @Override
    public void modify(GuestBookDTO dto) {

    }

    @Override
    public GuestBookDTO read(Long gno) {
        Optional<GuestBook> result = guestbookRepository.findById(gno);
        GuestBookDTO dto = null;

        return result.isPresent() ? convertEntity2DTO(result.get()) : null;
    }
}
