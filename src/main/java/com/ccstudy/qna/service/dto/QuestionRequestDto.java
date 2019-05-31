package com.ccstudy.qna.service.dto;

import com.ccstudy.qna.domain.entity.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class QuestionRequestDto {

    private String title;
    private String contents;
    private String userId;

    public Question toEntity(){
        return Question.createBuilder()
                .title(title)
                .contents(contents)
                .userId(userId)
                .build();
    }
}
