package com.ccstudy.qna.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String userId;

    @Setter
    private String email;

    @Setter
    private String password;

    @Setter
    private String name;

    @Builder
    public Account(String userId, String email, String password, String name) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.name = name;
    }
}
