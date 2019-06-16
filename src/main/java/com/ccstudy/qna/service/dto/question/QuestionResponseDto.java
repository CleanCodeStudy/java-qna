package com.ccstudy.qna.service.dto.question;

import com.ccstudy.qna.domain.entity.Question;
import com.ccstudy.qna.util.LocalDateTimeConverter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QuestionResponseDto {

    private Long id;
    private String title;
    private String createDate;
    private String modifyDate;

    public QuestionResponseDto(Question question) {
        this.id = question.getId();
        this.title = question.getTitle();
        this.createDate = LocalDateTimeConverter.convertLocalDate(question.getCreatedDateTime());
        this.modifyDate = LocalDateTimeConverter.convertLocalDate(question.getModifiedDateTime());
    }
}
