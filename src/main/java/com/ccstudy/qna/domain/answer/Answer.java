package com.ccstudy.qna.domain.answer;

import com.ccstudy.qna.domain.BaseTimeEntity;
import com.ccstudy.qna.domain.account.Account;
import com.ccstudy.qna.domain.question.Question;
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
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_answet_to_account"))
    private Account author;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_to_question"))
    private Question question;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String contents;

    private Boolean display = true;

    @Builder(builderMethodName = "createBuilder")
    public Answer(Account author, Question question, String title, String contents) {
        this.author = author;
        this.question = question;
        this.title = title;
        this.contents = contents;
    }

    public Long deleteAnswer() {
        this.display = false;
        return this.id;
    }

    public boolean isCorrectAccount(String email) {
        return this.author.getEmail().equals(email);
    }
}
