package com.ccstudy.qna.dto.question;

import com.ccstudy.qna.domain.question.Question;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class QuestionSaveRequestDto {

    @NotBlank
    private String title;

    @NotBlank
    private String contents;

    public QuestionSaveRequestDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public Question toEntity() {
        return Question.createBuilder()
                .title(title)
                .contents(contents)
                .build();
    }
}
