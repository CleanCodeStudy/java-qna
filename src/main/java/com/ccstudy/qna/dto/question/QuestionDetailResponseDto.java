package com.ccstudy.qna.dto.question;

import com.ccstudy.qna.domain.answer.Answer;
import com.ccstudy.qna.domain.question.Question;
import com.ccstudy.qna.dto.answer.AnswersResponseDto;
import com.ccstudy.qna.util.DateTimeConverter;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class QuestionDetailResponseDto {
    private Long id;
    private String title;
    private String author;
    private String contents;
    private String registerDate;
    private String updateDate;
    private List<AnswersResponseDto> answers;

    public QuestionDetailResponseDto(Question entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor().getLastName();
        this.contents = entity.getContents();
        this.registerDate = DateTimeConverter.toStringDate(entity.getRegisterDate());
        this.updateDate = DateTimeConverter.toStringDate(entity.getUpdateDate());
        this.answers = entity.getAnswers().stream()
                .filter(Answer::getDisplay)
                .map(AnswersResponseDto::new)
                .collect(Collectors.toList());
    }

}
