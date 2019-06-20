package com.ccstudy.qna.dto.Question;

import com.ccstudy.qna.domain.Account;
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

    public Question toEntity(Account account) {
        return Question.createBuilder()
                .title(this.title)
                .content(this.content)
                .account(account)
                .build();
    }
}
