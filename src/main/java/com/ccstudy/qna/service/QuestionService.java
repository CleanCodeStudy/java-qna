package com.ccstudy.qna.service;

import com.ccstudy.qna.domain.Question;
import com.ccstudy.qna.dto.QuestionDetailResDto;
import com.ccstudy.qna.dto.QuestionReqDto;
import com.ccstudy.qna.dto.QuestionResDto;
import com.ccstudy.qna.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    private QuestionRepository questionRepository = new QuestionRepository();

    public void createQuestion(QuestionReqDto questionReqDto) {
        Question question = questionReqDto.toEntity();
        questionRepository.saveQuestion(question);
    }

    public List<QuestionResDto> getQuestionBoard() {
        return questionRepository.findAllQuestion()
                .stream()
                .map(question -> new QuestionResDto(question))
                .collect(Collectors.toList());
    }

    public QuestionDetailResDto getQuestionDetail(Long index) {
        Question question = questionRepository.findQuestionById(index);
        return new QuestionDetailResDto(question);
    }
}
