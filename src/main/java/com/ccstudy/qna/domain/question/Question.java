package com.ccstudy.qna.domain.question;

import com.ccstudy.qna.domain.BaseTimeEntity;
import com.ccstudy.qna.domain.account.Account;
import com.ccstudy.qna.domain.answer.Answer;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

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

    @ManyToOne() // N+1 문제?
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_question_author"))
    private Account author;

    @Setter
    @Column(columnDefinition = "TEXT")
    private String contents;

    @OneToMany(mappedBy = "questionId", fetch = FetchType.EAGER)
    @Where(clause = "display = true")
    private List<Answer> answers;

    private Boolean display = true;

    @Builder(builderMethodName = "createBuilder")
    public Question(Account author, String title, String contents) {
        this.author = author;
        this.title = title;
        this.contents = contents;
    }

    public Long deleteQuestion() {
        this.display = false;
        return this.id;
    }


    public boolean isCorrectEmail(String sessionEmail) {
        return StringUtils.equals(author.getEmail(), sessionEmail);
    }

}
