package com.ccstudy.qna.service.dto.answer;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AnswerRequestDto {
    private String content;

}
