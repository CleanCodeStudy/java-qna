package com.ccstudy.qna.dto.Answer;

import com.ccstudy.qna.domain.Account;
import com.ccstudy.qna.domain.Answer;
import com.ccstudy.qna.domain.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class AnswerSaveReqDto {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    public Answer toEntity(Question question, Account account) {
        return Answer.createBuilder()
                .content(this.content)
                .title(this.title)
                .author(account)
                .question(question)
                .status(true)
                .build();
    }
}
