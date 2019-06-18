package com.ccstudy.qna.dto.Question;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@Builder
public class QuestionUpdateReqDto {
    @NotNull
    @NotBlank
    private String title;
    @NotNull
    @NotBlank
    private String content;
    @NotNull
    private String author;
}
