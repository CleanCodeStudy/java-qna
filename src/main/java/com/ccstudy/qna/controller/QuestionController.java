package com.ccstudy.qna.controller;

import com.ccstudy.qna.dto.QuestionReqDto;
import com.ccstudy.qna.dto.QuestionResDto;
import com.ccstudy.qna.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping("/questions")
    public String createQuestion(QuestionReqDto questionReqDto) {
        questionService.createQuestion(questionReqDto);
        return "/";
    }

    @GetMapping("/")
    public ModelAndView getQuestionBoard() {
        ModelAndView mav = new ModelAndView();
        List<QuestionResDto> questionResDtos = questionService.getQuestionBoard();
        mav.addObject("questions",questionResDtos);
        mav.setViewName("index");
        return mav;
    }

}
