package com.ccstudy.qna.service;

import com.ccstudy.qna.domain.Account;
import com.ccstudy.qna.domain.Question;
import com.ccstudy.qna.domain.repository.AccountRepository;
import com.ccstudy.qna.domain.repository.QuestionRepository;
import com.ccstudy.qna.dto.Question.QuestionDetailResDto;
import com.ccstudy.qna.dto.Question.QuestionResDto;
import com.ccstudy.qna.dto.Question.QuestionSaveReqDto;
import com.ccstudy.qna.dto.Question.QuestionUpdateReqDto;
import com.ccstudy.qna.exception.account.DeleteException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final AccountRepository accountRepository;

    public Long createQuestion(QuestionSaveReqDto questionSaveReqDto, Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(NoSuchElementException::new);
        Question question = questionSaveReqDto.toEntity(account);
        question = questionRepository.save(question);
        return question.getId();
    }

    public List<QuestionResDto> getQuestionBoard() {
        return questionRepository.findAllJoinFetch().stream()
                .map(QuestionResDto::new)
                .collect(Collectors.toList());
    }

    public QuestionDetailResDto getQuestionDetail(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
        return new QuestionDetailResDto(question);
    }

    @Transactional
    public Long updateQuestion(QuestionUpdateReqDto questionUpdateReqDto, Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
        update(question, questionUpdateReqDto);
        return question.getId();
    }

    private void update(Question question, QuestionUpdateReqDto questionUpdateReqDto) {
        question.setContent(questionUpdateReqDto.getContent());
        question.setTitle(questionUpdateReqDto.getTitle());
    }

    @Transactional
    public Long deleteQuestion(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
        boolean canRemoveQuestion = question.enableQuestionDelete();
        if(canRemoveQuestion){
            question.removeQuestion();
            return question.getId();
        }
        throw new DeleteException("다른 사람의 댓글이 남아있어서 지울 수 없는 질문 입니다.");
    }


}
