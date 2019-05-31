package com.ccstudy.qna.service.dto;

import com.ccstudy.qna.domain.entity.Question;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QuestionResponseDto {

    private String id;
    private String title;
    private String userId;
    private LocalDate createDate;
    private LocalDate modifyDate;

    public QuestionResponseDto(Question question) {
        this.id = question.getId();
        this.title = question.getTitle();
        this.userId = question.getUserId();
        this.createDate = question.getCreateDate();
        this.modifyDate = question.getModifyDate();
    }
}
