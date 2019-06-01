package com.ccstudy.qna.service;

import com.ccstudy.qna.domain.Question;
import com.ccstudy.qna.dto.QuestionDetailResDto;
import com.ccstudy.qna.dto.QuestionReqDto;
import com.ccstudy.qna.dto.QuestionResDto;
import com.ccstudy.qna.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {

    // Autowired = test code x, spring 을 실행해야 가능
    private final QuestionRepository questionRepository;

    // reflection : 기본 생성자, getter
    // object mapper : json -> object // object는 getter와 기본 생성자만 있는데 (allArg도아닌데) 어떻게 ? reflection 사용해서 주입하는 것


    public void createQuestion(QuestionReqDto questionReqDto) {
        Question question = questionReqDto.toEntity();
        questionRepository.saveQuestion(question);
    }

    public List<QuestionResDto> getQuestionBoard() {
        return questionRepository.findAllQuestion()
                .stream()
                .map(QuestionResDto::new)
                .collect(Collectors.toList());
    }

    public QuestionDetailResDto getQuestionDetail(Long index) {
        Question question = questionRepository.findQuestionById(index);
        return new QuestionDetailResDto(question);
    }
}
