package com.ccstudy.qna.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Answer extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_to_question"))
    private Question question;

    @JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_to_account"))
    @ManyToOne(fetch = FetchType.LAZY)
    private Account author;

    private String title;

    private String content;

    private boolean status;

    @Builder(builderMethodName = "createBuilder")
    public Answer(String content, String title, Account author, Question question, boolean status) {
        this.content = content;
        this.title = title;
        this.author = author;
        this.question = question;
        this.status = status;
    }

}
