package com.ccstudy.qna.domain.entity;

import com.ccstudy.qna.exception.UnAuthorizedException;
import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String email;

    @Setter
    private String password;

    @Setter
    @Embedded
    private UserName name;


    @Builder(builderMethodName = "createBuilder")
    private User(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.name = new UserName(firstName, lastName);
    }

    public void matchPassword(String password) {
        if (!this.password.equals(password)) {
            throw new UnAuthorizedException();
        }
    }

    public String getUserName() {
        return name.toString();
    }
}
