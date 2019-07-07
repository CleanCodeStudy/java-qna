package com.ccstudy.qna.dto.answer;

import com.ccstudy.qna.domain.account.Account;
import com.ccstudy.qna.domain.answer.Answer;
import com.ccstudy.qna.domain.question.Question;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AnswerSaveRequestDto {
    private String title;
    private String contents;

    public Answer toEntity(Account author, Question question) {
        return Answer.createBuilder()
                .author(author)
                .question(question)
                .title(this.title)
                .contents(this.contents)
                .build();
    }
}
