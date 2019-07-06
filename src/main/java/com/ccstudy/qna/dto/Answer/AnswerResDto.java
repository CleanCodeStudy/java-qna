package com.ccstudy.qna.dto.Answer;

import com.ccstudy.qna.domain.Answer;
import com.ccstudy.qna.util.DateTimeConverter;
import lombok.Getter;

@Getter
public class AnswerResDto {

    private Long id;
    private String title;
    private String registerDate;
    private String updateDate;

    public AnswerResDto(Answer answer) {
        this.id = answer.getId();
        this.title = answer.getTitle();
        this.registerDate = DateTimeConverter.getConvertedDate(answer.getRegisterDate());
        this.updateDate = DateTimeConverter.getConvertedDate(answer.getUpdateDate());
    }

}
