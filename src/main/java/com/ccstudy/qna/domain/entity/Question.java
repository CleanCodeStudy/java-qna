package com.ccstudy.qna.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Question extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String contents;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;

    @Builder(builderMethodName = "createBuilder")
    private Question(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public Question setUser(User user) {
        this.user = user;
        return this;
    }

    public String getWriterName() {
        return user.getUserName();
    }
}

