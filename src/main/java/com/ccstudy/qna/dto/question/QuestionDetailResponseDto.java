package com.ccstudy.qna.dto.question;

import com.ccstudy.qna.domain.question.Question;
import com.ccstudy.qna.util.StringConverter;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Getter
public class QuestionDetailResponseDto {
    private Long id;
    private String title;
    private String author;
    private String contents;
    private String registerDate;
    private String updateDate;

    public QuestionDetailResponseDto(Question entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.contents = entity.getContents();
        this.registerDate = StringConverter.toStringDate(entity.getRegisterDate());
        this.updateDate = StringConverter.toStringDate(entity.getUpdateDate());
    }

}
