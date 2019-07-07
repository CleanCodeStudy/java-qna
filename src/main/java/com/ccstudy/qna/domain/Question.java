package com.ccstudy.qna.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Where(clause = "status = true")
public class Question extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String title;
    @Setter
    private String content;

    @JoinColumn(foreignKey = @ForeignKey(name = "fk_question_account"))
    @ManyToOne(fetch = FetchType.LAZY)
    private Account author;

    private boolean status = true;

    @Where(clause = "status = true")
    @OneToMany(mappedBy = "question")
    private List<Answer> answers;

    @Builder(builderMethodName = "createBuilder")
    public Question(String title, String content, Account author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    //TODO: 리플렉션할때 기본생성자로 변경
    private Question(Long id, Question question) {
        super(LocalDateTime.now(), LocalDateTime.now());
        this.id = id;
        this.title = question.getTitle();
        this.author = question.getAuthor();
        this.content = question.getContent();
    }

    public void removeQuestion() {
        this.status = false;
    }

    public void checkAnswerStatus() {
        boolean cantRemove = this.answers.stream()
                .filter(this::isNotAuthor)
                .anyMatch(Answer::isStatus);
        if (cantRemove) {
            throw new RuntimeException("지울수 없습니다.");
        }
    }

    private boolean isNotAuthor(Answer answer) {
        return !answer.getAuthor()
                .getId()
                .equals(this.author.getId());
    }

}
