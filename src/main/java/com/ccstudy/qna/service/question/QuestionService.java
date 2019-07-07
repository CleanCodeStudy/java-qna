package com.ccstudy.qna.service.question;

import com.ccstudy.qna.domain.account.Account;
import com.ccstudy.qna.domain.account.AccountRepository;
import com.ccstudy.qna.domain.question.Question;
import com.ccstudy.qna.domain.question.QuestionRepository;
import com.ccstudy.qna.dto.account.LoginAccount;
import com.ccstudy.qna.dto.question.QuestionDetailResponseDto;
import com.ccstudy.qna.dto.question.QuestionListResponseDto;
import com.ccstudy.qna.dto.question.QuestionSaveRequestDto;
import com.ccstudy.qna.dto.question.QuestionUpdateRequestDto;
import com.ccstudy.qna.error.UnAuthenticationException;
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

    //등록하기
    @Transactional
    public Long save(QuestionSaveRequestDto dto, LoginAccount loginAccount) {
        Account findAccount = accountRepository.findAccountByEmail(loginAccount.getEmail()).
                orElseThrow(NoSuchElementException::new);
        return questionRepository.save(dto.toEntity(findAccount)).getId();
    }

    //조회하기
    @Transactional(readOnly = true)
    public List<QuestionListResponseDto> showQuestions() {
        return questionRepository.findAll().stream()
                .map(QuestionListResponseDto::new)
                .collect(Collectors.toList());
    }


    //게시글 한 개 조회하기
    @Transactional(readOnly = true)//조회만가능
    public QuestionDetailResponseDto showQuestionDetail(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(IllegalAccessError::new);
        return new QuestionDetailResponseDto(question);
    }

    //게시글 수정
    //TODO: Update의 경우 Dto 를 리턴시키고 View에 적용시키는 것이 좋을 듯
    @Transactional
    public Long updateQuestion(QuestionUpdateRequestDto dto, LoginAccount loginAccount) {
        Question findQuestion = questionRepository.findById(dto.getId())
                .orElseThrow(IllegalAccessError::new);
        if (findQuestion.isCorrectEmail(loginAccount.getEmail())) {
            findQuestion.setTitle(dto.getTitle());
            findQuestion.setContents(dto.getContents());
            return findQuestion.getId();
        }
        throw new UnAuthenticationException();
    }

    //게시글 삭제
    @Transactional
    public Long delete(Long id, LoginAccount loginAccount) {
        Question findQuestion = questionRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
        if (findQuestion.isCorrectEmail(loginAccount.getEmail())) {
            questionRepository.deleteById(id);
            return id;
        }
        throw new UnAuthenticationException();
    }

}