package com.ccstudy.qna.service.answer;

import com.ccstudy.qna.domain.account.Account;
import com.ccstudy.qna.domain.account.AccountRepository;
import com.ccstudy.qna.domain.answer.Answer;
import com.ccstudy.qna.domain.answer.AnswerRepository;
import com.ccstudy.qna.dto.account.LoginAccount;
import com.ccstudy.qna.dto.answer.AnswerDetailResponseDto;
import com.ccstudy.qna.dto.answer.AnswerSaveRequestDto;
import com.ccstudy.qna.error.UnAuthenticationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final AccountRepository accountRepository;

    //답변 등록하기
    @Transactional
    public Long saveAnswer(AnswerSaveRequestDto dto, LoginAccount loginAccount, Long questionId) {
        Account author = accountRepository.findAccountByEmail(loginAccount.getEmail())
                .orElseThrow(NoSuchElementException::new);

        return answerRepository.save(dto.toEntity(author, questionId)).getId();
    }

    //답변 조회
    @Transactional
    public AnswerDetailResponseDto getAnswerDetail(Long answerId) {
        Answer findAnswer = answerRepository.findById(answerId)
                .orElseThrow(NoSuchElementException::new);
        return new AnswerDetailResponseDto(findAnswer);
    }

    //답변 삭제하기
    @Transactional
    public Long deleteAnswer(Long id, LoginAccount loginAccount) {
        Answer findAnswer = answerRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);

        if (!findAnswer.isCorrectAccount(loginAccount.getEmail())) {
            throw new UnAuthenticationException();
        }

        findAnswer.deleteAnswer();

        return findAnswer.getQuestionId();
    }
}
