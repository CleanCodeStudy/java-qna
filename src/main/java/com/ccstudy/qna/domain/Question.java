package com.ccstudy.qna.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class Question {

    private Long id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime registerDate;
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
