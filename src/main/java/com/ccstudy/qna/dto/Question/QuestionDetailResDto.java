package com.ccstudy.qna.dto.Question;

import com.ccstudy.qna.domain.Question;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class QuestionDetailResDto {
    private Long id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime registerDate;
    private LocalDateTime updateDate;


    public QuestionDetailResDto(Question question) {
        this.id = question.getId();
        this.title = question.getTitle();
        this.content = question.getContent();
        this.author = question.getAuthor();
        this.registerDate = question.getRegisterDate();
        this.updateDate = question.getUpdateDate();
    }
}
