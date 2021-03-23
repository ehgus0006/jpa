package springboot.jpa.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GuestBook extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gno;

    @Column(length = 100 ,nullable = false)
    private String title;

    @Column(length = 1500, nullable = false)
    private String content;

    @Column(length = 50, nullable = false)
    private String writer;

    public GuestBook(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }
    public void changeTitle(String Title){
        this.content = Title;
    }

    public void changeContent(String content){
        this.content = content;
    }
}
