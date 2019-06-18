package com.ccstudy.qna.service;

import com.ccstudy.qna.domain.Question;
import com.ccstudy.qna.dto.Question.QuestionDetailResDto;
import com.ccstudy.qna.dto.Question.QuestionReqDto;
import com.ccstudy.qna.dto.Question.QuestionResDto;
import com.ccstudy.qna.dto.Question.QuestionUpdateReqDto;
import com.ccstudy.qna.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public void createQuestion(QuestionReqDto questionReqDto) {
        Question question = questionReqDto.toEntity();
        questionRepository.save(question);
    }

    public List<QuestionResDto> getQuestionBoard() {
        return questionRepository.findAll()
                .stream()
                .map(QuestionResDto::new)
                .collect(Collectors.toList());
    }

    public QuestionDetailResDto getQuestionDetail(Long index) {
        Question question = questionRepository.findById(index).orElseThrow(NoSuchElementException::new);
        return new QuestionDetailResDto(question);
    }

    @Transactional
    public void updateQuestion(QuestionUpdateReqDto questionUpdateReqDto, Long id) {
        Question question = questionRepository.findById(id).orElseThrow(NoSuchElementException::new);
        updateQuestion(question, questionUpdateReqDto);
    }

    public void updateQuestion(Question question, QuestionUpdateReqDto questionUpdateReqDto) {
        question.setAuthor(questionUpdateReqDto.getAuthor());
        question.setContent(questionUpdateReqDto.getContent());
        question.setTitle(questionUpdateReqDto.getTitle());
    }

    @Transactional
    public void deleteQuestion(Long id) {
        Question question = questionRepository.findById(id).orElseThrow(NoSuchElementException::new);
        questionRepository.delete(question);
    }
}
