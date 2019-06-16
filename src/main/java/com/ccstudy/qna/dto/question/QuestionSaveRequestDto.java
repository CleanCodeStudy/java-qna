package com.ccstudy.qna.dto.question;

import com.ccstudy.qna.domain.question.Question;

import javax.validation.constraints.NotBlank;


public class QuestionSaveRequestDto {

    @NotBlank
    private String title;

    @NotBlank
    private String author;

    @NotBlank
    private String contents;

    public QuestionSaveRequestDto(String title, String author, String contents) {
        this.title = title;
        this.author = author;
        this.contents = contents;
    }

    public Question toEntity() {
        return Question.createBuilder()
                .title(title)
                .contents(contents)
                .author(author)
                .build();
    }
}
