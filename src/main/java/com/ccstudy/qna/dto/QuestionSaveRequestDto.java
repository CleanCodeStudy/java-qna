package com.ccstudy.qna.dto;

import com.ccstudy.qna.domain.Question;


public class QuestionSaveRequestDto {

    private String title;
    private String author;
    private String contents;

    public QuestionSaveRequestDto(String title, String author, String contents) {
        this.title = title;
        this.author = author;
        this.contents = contents;
    }

    public Question toEntity() {
        return Question.builder()
                .title(title)
                .contents(contents)
                .author(author)
                .build();
    }
}
