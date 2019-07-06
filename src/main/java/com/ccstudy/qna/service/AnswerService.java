package com.ccstudy.qna.service;

import com.ccstudy.qna.domain.Account;
import com.ccstudy.qna.domain.Answer;
import com.ccstudy.qna.domain.Question;
import com.ccstudy.qna.domain.repository.AccountRepository;
import com.ccstudy.qna.domain.repository.AnswerRepository;
import com.ccstudy.qna.domain.repository.QuestionRepository;
import com.ccstudy.qna.dto.Account.AccountAuthDto;
import com.ccstudy.qna.dto.Answer.AnswerDetailResDto;
import com.ccstudy.qna.dto.Answer.AnswerSaveReqDto;
import com.ccstudy.qna.exception.account.AuthException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Slf4j
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final AccountRepository accountRepository;
    private final QuestionRepository questionRepository;

    @Transactional(readOnly = true)
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


    @Transactional
    public void removeAnswer(Long answerId, AccountAuthDto authDto) {
        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(NoSuchElementException::new);
        validateId(answer, authDto);
        answer.removeAnswer();
    }

    private void validateId(Answer answer, AccountAuthDto authDto) {
        boolean isAuthor = answer.getAuthor()
                .getId()
                .equals(authDto.getId());
        if (!isAuthor) {
            throw new AuthException("권한이 없습니다.");
        }
    }
}
