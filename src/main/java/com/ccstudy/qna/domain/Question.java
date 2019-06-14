package com.ccstudy.qna.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class Question {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //없는게 나을 수도
    @Setter
    private String title;
    @Setter
    private String content;
    @Setter
    private String author;
    private LocalDateTime registerDate;
    @Setter
    private LocalDateTime updateDate;

    @Builder
    public Question(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.registerDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();
    }

}
