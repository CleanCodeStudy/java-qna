package com.ccstudy.qna.service.question;

import com.ccstudy.qna.domain.question.Question;
import com.ccstudy.qna.domain.question.QuestionRepository;
import com.ccstudy.qna.domain.question.QuestionRepositoryImpl;
import com.ccstudy.qna.dto.question.QuestionDetailResponseDto;
import com.ccstudy.qna.dto.question.QuestionListResponseDto;
import com.ccstudy.qna.dto.question.QuestionSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

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