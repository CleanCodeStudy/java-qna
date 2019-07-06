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

    @NotBlank
    private String title;

    @NotBlank
    private String content;
}
