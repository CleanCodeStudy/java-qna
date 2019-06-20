package com.ccstudy.qna.dto.Question;

import com.ccstudy.qna.domain.Question;
import com.ccstudy.qna.util.DateTimeConverter;
import lombok.Getter;

@Getter
public class QuestionResDto {

    private Long id;
    private String title;
    private String author;
    private String registerDate;
    private String updateDate;

    public QuestionResDto(Question question) {
        this.id = question.getId();
        this.title = question.getTitle();
        this.author = question.getAuthor().getUserId();
        this.registerDate = DateTimeConverter.getConvertedDate(question.getRegisterDate());
        this.updateDate = DateTimeConverter.getConvertedDate(question.getUpdateDate());
    }
}
