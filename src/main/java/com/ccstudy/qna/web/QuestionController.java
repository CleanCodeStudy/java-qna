package com.ccstudy.qna.web;

import com.ccstudy.qna.service.QuestionService;
import com.ccstudy.qna.service.dto.question.QuestionRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping
    public String save(@Valid QuestionRequestDto requestDto) {
        questionService.save(requestDto);
        return "redirect:/";
    }

}
