package com.ccstudy.qna.service;

import com.ccstudy.qna.domain.Question;
import com.ccstudy.qna.domain.QuestionRepository;
import com.ccstudy.qna.dto.QuestionDetailResponseDto;
import com.ccstudy.qna.dto.QuestionListResponseDto;
import com.ccstudy.qna.dto.QuestionSaveRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    private QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    //등록하기
    public int save(QuestionSaveRequestDto dto) {
        return questionRepository.save(dto.toEntity());
    }

    //조회하기
    public List<QuestionListResponseDto> showQuestions() {
        List<QuestionListResponseDto> questionResList = new ArrayList<>();
        int index = 1;

        for (Question question : questionRepository.getQuestionList()) {
            questionResList.add(new QuestionListResponseDto(question, index++));
        }

        return questionResList;
    }


    //게시글 한 개 조회하기
    public QuestionDetailResponseDto showQuestionDetail(int index) {
        Question question = questionRepository.getQuestionDetail(index - 1);
        return new QuestionDetailResponseDto(question);
    }
}
