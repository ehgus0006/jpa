package springboot.jpa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Getter
@Setter
@AllArgsConstructor
public class PageRequestDTO {

    private int page;
    private int size;

    public Pageable getPageable(Sort gno){
        return PageRequest.of(page - 1, size, Sort.by("gno").descending());
    }

    public PageRequestDTO(){
        page = 1;
        size = 10;
    }
}
