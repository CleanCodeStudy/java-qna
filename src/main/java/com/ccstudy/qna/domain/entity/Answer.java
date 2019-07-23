package com.ccstudy.qna.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Answer extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contents;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;

    @JoinColumn(name = "question_id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Question question;


    @Builder(builderMethodName = "createBuilder")
    private Answer(String contents, User user) {
        this.contents = contents;
        this.user = user;
    }

    public void addWriter(User user) {
        this.user = user;
    }

    public void postAnswer(Question question) {
        this.question = question;
        question.addAnswer(this);
    }
}
