package com.ccstudy.qna.web;

import com.ccstudy.qna.service.QuestionService;
import com.ccstudy.qna.service.dto.QuestionRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping
    public String save(QuestionRequestDto requestDto){
        questionService.save(requestDto);
        return "redirect:/";
    }

}
