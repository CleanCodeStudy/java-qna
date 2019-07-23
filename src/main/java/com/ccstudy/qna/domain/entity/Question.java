package com.ccstudy.qna.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Question extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String contents;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "question")
    private List<Answer> answers;

    @Builder(builderMethodName = "createBuilder")
    private Question(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public void addWriter(User user) {
        this.user = user;
    }

    public String getWriterName() {
        return user.getUserName();
    }

    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }
}

