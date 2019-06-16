package com.ccstudy.qna.service.dto.question;

import com.ccstudy.qna.domain.entity.Question;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QuestionRequestDto {

    @NotBlank
    private String title;

    @NotBlank
    private String contents;

    @Builder(builderMethodName = "testBuilder")
    private QuestionRequestDto(@NotBlank String title, @NotBlank String contents) {
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
