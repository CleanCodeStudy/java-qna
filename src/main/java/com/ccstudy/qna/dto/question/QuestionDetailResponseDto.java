package com.ccstudy.qna.dto.question;

import com.ccstudy.qna.domain.question.Question;
import com.ccstudy.qna.util.DateTimeConverter;
import lombok.Getter;

@Getter
public class QuestionDetailResponseDto {
    private Long id;
    private String title;
    private String author;
    private String contents;
    private String registerDate;
    private String updateDate;

    public QuestionDetailResponseDto(Question entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor().getLastName();
        this.contents = entity.getContents();
        this.registerDate = DateTimeConverter.toStringDate(entity.getRegisterDate());
        this.updateDate = DateTimeConverter.toStringDate(entity.getUpdateDate());
    }

}
