package com.ccstudy.qna.dto.Question;

import com.ccstudy.qna.domain.Question;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class QuestionResDto {

    private Long id;
    private String title;
    private String author;
    private LocalDateTime registerDate;
    private LocalDateTime updateDate;

    public QuestionResDto(Question question) {
        this.id = question.getId();
        this.title = question.getTitle();
        this.author = question.getAuthor();
        this.registerDate = question.getRegisterDate();
        this.updateDate = question.getUpdateDate();
    }
}
