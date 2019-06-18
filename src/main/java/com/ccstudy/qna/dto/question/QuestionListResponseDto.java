package com.ccstudy.qna.dto.question;

import com.ccstudy.qna.domain.question.Question;
import com.ccstudy.qna.util.DateTimeConverter;
import lombok.Getter;

@Getter
public class QuestionListResponseDto {

    private Long id;
    private String title;
    private String author;
    private String registerDate;
    private String updateDate;

    public QuestionListResponseDto(Question entity) {

        this.id=entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor().getLastName();
        this.registerDate = DateTimeConverter.toStringDate(entity.getRegisterDate());
        this.updateDate = DateTimeConverter.toStringDate(entity.getUpdateDate());
    }

}
