package com.ccstudy.qna.dto;

import com.ccstudy.qna.domain.Question;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class QuestionResDto {

    private String title;
    private String content;
    private String author;
    private LocalDateTime registerDate;
    private LocalDateTime updateDate;

    public QuestionResDto(Question question) {
        this.title = question.getTitle();
        this.content = question.getContent();
        this.author = question.getAuthor();
        this.registerDate = question.getRegisterDate();
        this.updateDate = question.getUpdateDate();
    }
}
