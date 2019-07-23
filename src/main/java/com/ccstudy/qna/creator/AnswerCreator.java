package com.ccstudy.qna.creator;

import com.ccstudy.qna.domain.entity.Answer;
import com.ccstudy.qna.domain.entity.Question;
import com.ccstudy.qna.domain.entity.User;
import com.ccstudy.qna.domain.repository.AnswerRepository;
import com.ccstudy.qna.service.dto.answer.AnswerRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AnswerCreator {
    private final AnswerRepository answerRepository;

    @Transactional
    public void addAnswer(User writer, Question question, AnswerRequestDto requestDto) {
        Answer answer = writeAnswer(requestDto, writer);
        answer.postAnswer(question);
    }

    private Answer writeAnswer(AnswerRequestDto requestDto, User writer) {
        return Answer.createBuilder()
                .contents(requestDto.getContent())
                .user(writer)
                .build();
    }

}
