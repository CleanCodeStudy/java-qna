package com.ccstudy.qna.service;

import com.ccstudy.qna.domain.entity.User;
import com.ccstudy.qna.domain.repository.QuestionRepository;
import com.ccstudy.qna.domain.repository.UserRepository;
import com.ccstudy.qna.domain.support.AccessUserStore;
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

    private final UserRepository userRepository;

    public void save(QuestionRequestDto requestDto){
        User user = userRepository.findById(AccessUserStore.getUserId())
                .orElseThrow(RuntimeException::new);

        questionRepository.save(requestDto.toEntity().setUser(user));
    }

    public List<QuestionResponseDto> findAll(){
        return questionRepository.findAllJoinFetch().stream()
                .map(QuestionResponseDto::new)
                .collect(Collectors.toList());
    }
}
