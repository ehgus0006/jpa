package springboot.jpa.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import springboot.jpa.dto.GuestBookDTO;
import springboot.jpa.dto.PageRequestDTO;
import springboot.jpa.dto.PageResultDTO;
import springboot.jpa.entity.GuestBook;
import springboot.jpa.entity.QGuestBook;
import springboot.jpa.repository.GuestbookRepository;

import java.util.Optional;
import java.util.function.Function;

@Service
@Slf4j
public class GuestBookServiceImpl implements GuestBookService{

    @Autowired
    private GuestbookRepository guestbookRepository;

    private BooleanBuilder getSearch(PageRequestDTO requestDTO) {
        String type = requestDTO.getType();
        String keyword = requestDTO.getKeyword();
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QGuestBook qGuestBook = QGuestBook.guestBook;

        if(type == null){
            return booleanBuilder;
        }
        if (type.contains("t")) {
            //BooleanExpression exp = qGuestBook.title.contains(keyword); // title like '%....%;
            booleanBuilder.or(qGuestBook.title.contains(keyword));
        }
        if (type.contains("c")) {
            booleanBuilder.or(qGuestBook.content.contains(keyword));
        }
        if (type.contains("w")) {
            booleanBuilder.or(qGuestBook.writer.contains(keyword));
        }

        return booleanBuilder;
    }

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

        BooleanBuilder booleanBuilder = getSearch(requestDTO);

        Page<GuestBook> result = guestbookRepository.findAll(booleanBuilder,pageable);

        Function<GuestBook, GuestBookDTO> fn = (entity -> convertEntity2DTO(entity));

        return new PageResultDTO<GuestBookDTO, GuestBook>(result,fn);
    }

    @Override
    public void remove(Long gno) {
        guestbookRepository.deleteById(gno);
    }

    @Override
    public void modify(GuestBookDTO dto) {
        Optional<GuestBook> result = guestbookRepository.findById(dto.getGno());
        if (result.isPresent()) {
            GuestBook entity = result.get();
            entity.changeTitle(dto.getTitle());
            entity.changeContent(dto.getContent());
            guestbookRepository.save(entity);
        }
    }

    @Override
    public GuestBookDTO read(Long gno) {
        Optional<GuestBook> result = guestbookRepository.findById(gno);
        GuestBookDTO dto = null;

        return result.isPresent() ? convertEntity2DTO(result.get()) : null;
    }
}
