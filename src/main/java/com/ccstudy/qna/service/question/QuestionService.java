package com.ccstudy.qna.service.question;

import com.ccstudy.qna.domain.question.Question;
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

    private final QuestionRepositoryImpl questionRepository;

    //private final -- > RequiredArgument~~~~ bean 주입 대상

    //@Autowired
    //spring을 안띄우면 테스트를 못함


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