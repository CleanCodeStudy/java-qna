package com.ccstudy.qna.domain.question;

import com.ccstudy.qna.domain.BaseTimeEntity;
import com.ccstudy.qna.domain.account.Account;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Entity
public class Question extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_question_author"))
    private Account account;

    @Column(nullable = false)
    private String author;

    @Column(columnDefinition = "TEXT")
    private String contents;

    @Builder(builderMethodName = "createBuilder")
    public Question(String title, String author, String contents) {
        this.title = title;
        this.author = author;
        this.contents = contents;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
