package com.ccstudy.qna.service;

import com.ccstudy.qna.domain.Account;
import com.ccstudy.qna.domain.Answer;
import com.ccstudy.qna.domain.Question;
import com.ccstudy.qna.domain.repository.AccountRepository;
import com.ccstudy.qna.domain.repository.AnswerRepository;
import com.ccstudy.qna.domain.repository.QuestionRepository;
import com.ccstudy.qna.dto.Answer.AnswerDetailResDto;
import com.ccstudy.qna.dto.Answer.AnswerSaveReqDto;
import com.ccstudy.qna.dto.Question.QuestionDetailResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@Slf4j
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final AccountRepository accountRepository;
    private final QuestionRepository questionRepository;

    public Long createAnswer(AnswerSaveReqDto answerSaveReqDto, Long accountId, Long questionId) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(NoSuchElementException::new);
        Question question = questionRepository.findById(questionId)
                .orElseThrow(NoSuchElementException::new);

        Answer answer = answerSaveReqDto.toEntity(question, account);
        answer = answerRepository.save(answer);

        return answer.getId();
    }

    public AnswerDetailResDto getAnswerDetail(Long answerId) {
        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(NoSuchElementException::new);
        return new AnswerDetailResDto(answer);
    }


}
