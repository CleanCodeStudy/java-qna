package com.ccstudy.qna.service;

import com.ccstudy.qna.creator.AnswerCreator;
import com.ccstudy.qna.domain.entity.Question;
import com.ccstudy.qna.domain.entity.User;
import com.ccstudy.qna.domain.repository.QuestionRepository;
import com.ccstudy.qna.domain.service.UserFindService;
import com.ccstudy.qna.service.dto.answer.AnswerRequestDto;
import com.ccstudy.qna.service.dto.question.QuestionRequestDto;
import com.ccstudy.qna.service.dto.question.QuestionResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    private final UserFindService userFindService;

    private final AnswerCreator answerCreator;

    public void save(Long userId, QuestionRequestDto requestDto) {
        User user = userFindService.findUser(userId);

        Question question = requestDto.toEntity();
        question.addWriter(user);

        questionRepository.save(question);
    }

    public void addAnswer(Long userId, Long questionId, AnswerRequestDto requestDto) {
        User user = userFindService.findUser(userId);
        Question question = questionRepository.findById(questionId)
                .orElseThrow(RuntimeException::new);

        answerCreator.addAnswer(user, question, requestDto);
    }

    public List<QuestionResponseDto> findAll() {
        return questionRepository.findAllJoinFetch().stream()
                .map(QuestionResponseDto::new)
                .collect(Collectors.toList());
    }


}
