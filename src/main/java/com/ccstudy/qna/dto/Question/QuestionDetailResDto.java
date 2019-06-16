package com.ccstudy.qna.dto.Question;

import com.ccstudy.qna.domain.Question;
import com.ccstudy.qna.util.DateTimeConverter;
import lombok.Getter;

@Getter
public class QuestionDetailResDto {
    private Long id;
    private String title;
    private String content;
    private String author;
    private String registerDate;
    private String updateDate;


    public QuestionDetailResDto(Question question) {
        this.id = question.getId();
        this.title = question.getTitle();
        this.content = question.getContent();
        this.author = question.getAuthor();
        this.registerDate = DateTimeConverter.getConvertedDate(question.getRegisterDate());
        this.updateDate = DateTimeConverter.getConvertedDate(question.getUpdateDate());
    }
}
