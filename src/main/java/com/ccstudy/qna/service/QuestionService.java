package com.ccstudy.qna.service;

import com.ccstudy.qna.domain.repository.QuestionRepository;
import com.ccstudy.qna.service.dto.QuestionRequestDto;
import com.ccstudy.qna.service.dto.QuestionResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;


    public void save(QuestionRequestDto requestDto){
        questionRepository.save(requestDto.toEntity());
    }

    public List<QuestionResponseDto> findAll(){
        return questionRepository.findAll().stream()
                .map(QuestionResponseDto::new)
                .collect(Collectors.toList());
    }
}
