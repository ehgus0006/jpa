package springboot.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.jdo.annotations.Join;
import javax.persistence.*;

/* lazy 로딩일때는 ToString 메소드를 사용하지 마라
* @ToString을 이용하면 writer 변수로 선언된 Member 객체 역시 출력해야한다 
* Member을 출력하기 위해서는 Member객체의 toString()도 호출해야하고 이때 데이터베이스 연결이 필요해서 
* 연관관계가 있는 엔티티 클래스의 경우 @ToString을 할때는 습관적으로 exclude속성을 사용하는 것이 좋습니다.
* 지연로딩할때는 반드시 지정해주는것이 좋음!!!!!!!!!!!!!1ㅂ
* */
@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;
    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email")
    private Member writer;

    public Board(String title, String content, Member writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public Board(long bno) {
        this.bno = bno;
    }
}
