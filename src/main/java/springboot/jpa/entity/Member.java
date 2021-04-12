package springboot.jpa.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Member extends BaseEntity {

    @Id
    @Column(length = 32)
    private String email;
    private String password;
    private String name;


    public Member(String email) {
        this.email = email;
    }
}
