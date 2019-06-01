package com.ccstudy.qna.service;

import com.ccstudy.qna.domain.Question;
import com.ccstudy.qna.dto.Question.QuestionDetailResDto;
import com.ccstudy.qna.dto.Question.QuestionReqDto;
import com.ccstudy.qna.dto.Question.QuestionResDto;
import com.ccstudy.qna.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
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
        questionRepository.save(question);
    }

    public List<QuestionResDto> getQuestionBoard() {
        return questionRepository.findAll()
                .stream()
                .map(QuestionResDto::new)
                .collect(Collectors.toList());
    }

    public QuestionDetailResDto getQuestionDetail(Long index) {
        Question question = questionRepository.findById(index).orElseThrow(NoSuchElementException::new);
        return new QuestionDetailResDto(question);
    }
}
