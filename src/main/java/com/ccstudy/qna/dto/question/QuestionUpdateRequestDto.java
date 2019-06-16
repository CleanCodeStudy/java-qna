package com.ccstudy.qna.dto.question;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QuestionUpdateRequestDto {
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String contents;

    @Builder
    public QuestionUpdateRequestDto(Long id, String title, String contents) {
        this.id = id;
        this.title = title;
        this.contents = contents;
    }
}
