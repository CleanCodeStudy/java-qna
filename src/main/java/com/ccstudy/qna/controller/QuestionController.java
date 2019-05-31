package com.ccstudy.qna.controller;

import com.ccstudy.qna.dto.QuestionDetailResDto;
import com.ccstudy.qna.dto.QuestionReqDto;
import com.ccstudy.qna.dto.QuestionResDto;
import com.ccstudy.qna.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequiredArgsConstructor
@Controller
@Slf4j
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping("/questions")
    public String createQuestion(@ModelAttribute QuestionReqDto questionReqDto) {
        questionService.createQuestion(questionReqDto);
        return "redirect:/";
    }

    @GetMapping("/")
    public ModelAndView getQuestionBoard() {
        ModelAndView mav = new ModelAndView();
        List<QuestionResDto> questionResDto = questionService.getQuestionBoard();
        mav.addObject("questions", questionResDto);
        mav.setViewName("index");
        return mav;
    }

    @GetMapping("/questions/{index}")
    public ModelAndView getQuestionDetails(@PathVariable("index") Long index){
        ModelAndView mav = new ModelAndView();
        QuestionDetailResDto resDto = questionService.getQuestionDetail(index);
        mav.addObject("question",resDto);
        mav.setViewName("/qna/show");
        return mav;
    }

}
