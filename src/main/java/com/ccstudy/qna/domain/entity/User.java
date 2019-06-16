package com.ccstudy.qna.domain.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String email;

    @Setter
    private String password;

    @Setter
    private String firstName;

    @Setter
    private String lastName;

//    @OneToMany(mappedBy = "user")
//    private List<Question> question;

    @Builder(builderMethodName = "createBuilder")
    public User(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
