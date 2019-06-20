package com.ccstudy.qna.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Question extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //없는게 나을 수도
    @Setter
    private String title;
    @Setter
    private String content;

    @JoinColumn(foreignKey = @ForeignKey(name="fk_question_account"))
    @ManyToOne(fetch = FetchType.LAZY)
    private Account author;


    @Builder(builderMethodName = "createBuilder")
    public Question(String title, String content, Account account) {
        this.title = title;
        this.content = content;
        this.author = account;
    }

    private Question(Long id, Question question){
        super(LocalDateTime.now(),LocalDateTime.now());
        this.id = id;
        this.title = question.getTitle();
        this.author = question.getAuthor();
        this.content = question.getContent();
    }

}
