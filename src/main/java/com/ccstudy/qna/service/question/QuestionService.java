package com.ccstudy.qna.service.question;

import com.ccstudy.qna.domain.question.Question;
import com.ccstudy.qna.domain.question.QuestionRepository;

import com.ccstudy.qna.dto.question.QuestionDetailResponseDto;
import com.ccstudy.qna.dto.question.QuestionListResponseDto;
import com.ccstudy.qna.dto.question.QuestionUpdateRequestDto;
import com.ccstudy.qna.dto.question.QuestionSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    //등록하기
    @Transactional
    public Long save(QuestionSaveRequestDto dto) {
        return questionRepository.save(dto.toEntity()).getId();
    }

    //조회하기
    @Transactional
    public List<QuestionListResponseDto> showQuestions() {
        return questionRepository.findAll().stream()
                .map(QuestionListResponseDto::new)
                .collect(Collectors.toList());
    }


    //게시글 한 개 조회하기
    @Transactional
    public QuestionDetailResponseDto showQuestionDetail(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(IllegalAccessError::new);
        return new QuestionDetailResponseDto(question);
    }

    //게시글 수정
    @Transactional
    public Long updateQuestion(QuestionUpdateRequestDto dto){
        Question findQuestion = questionRepository.findById(dto.getId())
                .orElseThrow(IllegalAccessError::new);
        findQuestion.setTitle(dto.getTitle());
        findQuestion.setContents(dto.getContents());
        return findQuestion.getId();
    }

    //게시글 삭제
    @Transactional
    public Long delete(Long id){
        questionRepository.deleteById(id);
        return id;
    }

}