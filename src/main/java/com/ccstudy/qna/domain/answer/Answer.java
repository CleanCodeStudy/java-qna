package com.ccstudy.qna.domain.answer;

import com.ccstudy.qna.domain.BaseTimeEntity;
import com.ccstudy.qna.domain.account.Account;
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

    @Column(nullable = false)
    private Long questionId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String contents;

    private Boolean display = true;

    @Builder(builderMethodName = "createBuilder")
    public Answer(Account author, Long questionId, String title, String contents) {
        this.author = author;
        this.questionId = questionId;
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
