package com.ccstudy.qna.dto.answer;

import com.ccstudy.qna.domain.answer.Answer;
import com.ccstudy.qna.util.DateTimeConverter;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AnswerDetailResponseDto {
    private Long id;
    private String author;
    private String title;
    private String contents;
    private String registerDate;
    private String updateDate;

    @Builder(builderMethodName = "createBuilder")
    public AnswerDetailResponseDto(Answer entity) {
        this.id = entity.getId();
        this.author = entity.getAuthor().getFirstName();
        this.title = entity.getTitle();
        this.contents = entity.getContents();
        this.registerDate = DateTimeConverter.toStringDate(entity.getRegisterDate());
        this.updateDate = DateTimeConverter.toStringDate(entity.getUpdateDate());
    }
}
