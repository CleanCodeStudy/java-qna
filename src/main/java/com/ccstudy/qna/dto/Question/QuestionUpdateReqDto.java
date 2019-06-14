package com.ccstudy.qna.dto.Question;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class QuestionUpdateReqDto {
    @NotNull
    private String title;
    @NotNull
    private String content;
    @NotNull
    private String author;
}
