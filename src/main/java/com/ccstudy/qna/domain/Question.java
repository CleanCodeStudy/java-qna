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
public class Question extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String title;
    @Setter
    private String content;

    @JoinColumn(foreignKey = @ForeignKey(name="fk_question_account"))
    @ManyToOne(fetch = FetchType.LAZY)
    private Account author;

//    @Where(clause = "status:true")
    @OneToMany(mappedBy="question")
    private List<Answer> answers;

    @Builder(builderMethodName = "createBuilder")
    public Question(String title, String content, Account author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    private Question(Long id, Question question){
        super(LocalDateTime.now(),LocalDateTime.now());
        this.id = id;
        this.title = question.getTitle();
        this.author = question.getAuthor();
        this.content = question.getContent();
    }

}
