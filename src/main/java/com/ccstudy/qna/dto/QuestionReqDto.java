package com.ccstudy.qna.dto;

import com.ccstudy.qna.domain.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class QuestionReqDto {
    private String title;
    private String content;
    private String author;

    public Question toEntity() {
        return Question.builder()
                .title(this.title)
                .content(this.content)
                .author(this.author)
                .build();
    }
}
