package com.ccstudy.qna.dto.Question;

import com.ccstudy.qna.domain.Question;
import com.ccstudy.qna.util.DateTimeConverter;
import lombok.Builder;
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
        this.author = question.getAuthor().getUserId();
        this.registerDate = DateTimeConverter.getConvertedDate(question.getRegisterDate());
        this.updateDate = DateTimeConverter.getConvertedDate(question.getUpdateDate());
    }

    @Builder(builderMethodName = "testBuilder")
    private QuestionDetailResDto(Long id, String title, String content, String author, String registerDate, String updateDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.registerDate = registerDate;
        this.updateDate = updateDate;
    }
}
