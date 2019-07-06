package com.ccstudy.qna.service;

import com.ccstudy.qna.domain.Account;
import com.ccstudy.qna.domain.Question;
import com.ccstudy.qna.domain.repository.AccountRepository;
import com.ccstudy.qna.domain.repository.QuestionRepository;
import com.ccstudy.qna.dto.Account.AccountAuthDto;
import com.ccstudy.qna.dto.Question.QuestionDetailResDto;
import com.ccstudy.qna.dto.Question.QuestionReqDto;
import com.ccstudy.qna.dto.Question.QuestionResDto;
import com.ccstudy.qna.dto.Question.QuestionUpdateReqDto;
import com.ccstudy.qna.exception.account.AuthException;
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
    private final AccountRepository accountRepository;

    public void createQuestion(QuestionReqDto questionReqDto, Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(NoSuchElementException::new);
        Question question = questionReqDto.toEntity(account);
        questionRepository.save(question);
    }

    public List<QuestionResDto> getQuestionBoard() {
        return questionRepository.findAllJoinFetch().stream()
                .map(QuestionResDto::new)
                .collect(Collectors.toList());
    }

    public QuestionDetailResDto getQuestionDetail(Long index) {
        Question question = questionRepository.findById(index)
                .orElseThrow(NoSuchElementException::new);
        return new QuestionDetailResDto(question);
    }

    @Transactional
    public void updateQuestion(QuestionUpdateReqDto questionUpdateReqDto, Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
        update(question, questionUpdateReqDto);
    }

    private void update(Question question, QuestionUpdateReqDto questionUpdateReqDto) {
        question.setContent(questionUpdateReqDto.getContent());
        question.setTitle(questionUpdateReqDto.getTitle());
    }

    @Transactional
    public void deleteQuestion(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
        questionRepository.delete(question);
    }

}
