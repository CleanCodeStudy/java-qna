package com.ccstudy.qna.dto.Answer;

import com.ccstudy.qna.domain.Answer;
import com.ccstudy.qna.util.DateTimeConverter;
import lombok.Getter;

@Getter
public class AnswerDetailResDto {
    private Long id;
    private String title;
    private String content;
    private String author;
    private String registerDate;
    private String updateDate;
    private Long questionId;
    private String questionTitle;

    public AnswerDetailResDto(Answer answer) {
        this.id = answer.getId();
        this.title = answer.getTitle();
        this.registerDate = DateTimeConverter.getConvertedDate(answer.getRegisterDate());
        this.updateDate = DateTimeConverter.getConvertedDate(answer.getUpdateDate());
        this.content = answer.getContent();
        this.author = answer.getAuthor()
                .getName();
        this.questionId = answer.getQuestion()
                .getId();
        this.questionTitle = answer.getQuestion()
                .getTitle();

    }
}
