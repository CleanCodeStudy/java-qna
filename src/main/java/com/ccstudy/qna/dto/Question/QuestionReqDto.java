package com.ccstudy.qna.dto.Question;

import com.ccstudy.qna.domain.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class QuestionReqDto {
    @NotNull
    @NotBlank
    private String title;
    @NotNull
    @NotBlank
    private String content;
    @NotNull
    private String author;

    public Question toEntity() {
        return Question.builder()
                .title(this.title)
                .content(this.content)
                .author(this.author)
                .build();
    }
}
