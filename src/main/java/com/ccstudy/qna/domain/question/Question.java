package com.ccstudy.qna.domain.question;

import com.ccstudy.qna.domain.BaseTimeEntity;
import com.ccstudy.qna.domain.account.Account;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Entity
public class Question extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private String title;

    @ManyToOne()
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_question_author"))
    private Account author;

    @Setter
    @Column(columnDefinition = "TEXT")
    private String contents;

    @Builder(builderMethodName = "createBuilder")
    public Question(Account author, String title, String contents) {
        this.author = author;
        this.title = title;
        this.contents = contents;
    }

    public boolean isCorrectEmail(String sessionEmail) {
        return StringUtils.equals(author.getEmail(), sessionEmail);
    }

}
