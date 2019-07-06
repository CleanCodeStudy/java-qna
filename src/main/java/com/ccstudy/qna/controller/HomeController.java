package com.ccstudy.qna.controller;

import com.ccstudy.qna.dto.Question.QuestionResDto;
import com.ccstudy.qna.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final QuestionService questionService;

    @GetMapping("/")
    public String getQuestionBoard(Model model) {
        List<QuestionResDto> questionResDto = questionService.getQuestionBoard();
        model.addAttribute("questions", questionResDto);
        return "/pages/index";
    }
}
