package springboot.jpa.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Setter
@AllArgsConstructor
@ToString
public class BoardDTO {

    private Long bno;
    private String title;
    private String content;
    private LocalDateTime regDate, modDate;
    private String writerEmail; // 작성자의 이메일(Member) 테이블의 pk
    private String writerName;  // 작성자의 이름
    private int replyCount; // 해당 게시글의 댓글수 (Reply) 테이블의 pk



}
