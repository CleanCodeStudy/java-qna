package com.ccstudy.qna.dto.Question;

import com.ccstudy.qna.domain.Answer;
import com.ccstudy.qna.domain.Question;
import com.ccstudy.qna.dto.Answer.AnswerResDto;
import com.ccstudy.qna.util.DateTimeConverter;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class QuestionResDto {

    private Long id;
    private String title;
    private String author;
    private String registerDate;
    private String updateDate;
    private List<AnswerResDto> answerResDtos;

    public QuestionResDto(Question question) {
        this.id = question.getId();
        this.title = question.getTitle();
        this.author = question.getAuthor().getUserId();
        this.registerDate = DateTimeConverter.getConvertedDate(question.getRegisterDate());
        this.updateDate = DateTimeConverter.getConvertedDate(question.getUpdateDate());
        this.answerResDtos = question.getAnswers().stream()
                .map(AnswerResDto::new)
                .collect(Collectors.toList());
    }

    @Builder(builderMethodName = "testBuilder")
    private QuestionResDto(Long id, String title, String author, String registerDate, String updateDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.registerDate = registerDate;
        this.updateDate = updateDate;
    }
}
