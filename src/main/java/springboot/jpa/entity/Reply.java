package springboot.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Reply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;
    private String text;
    private String replyer;

    @ManyToOne
    @JoinColumn(name = "bno")
    private Board board;

    public Reply(String text, Board board ,String replyer) {
        this.text = text;
        this.board = board;
        this.replyer = replyer;
    }
}
