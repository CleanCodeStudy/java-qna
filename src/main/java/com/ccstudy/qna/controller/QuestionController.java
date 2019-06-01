package com.ccstudy.qna.controller;

import com.ccstudy.qna.dto.Question.QuestionDetailResDto;
import com.ccstudy.qna.dto.Question.QuestionReqDto;
import com.ccstudy.qna.dto.Question.QuestionResDto;
import com.ccstudy.qna.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String getQuestionBoard(Model model) {
        List<QuestionResDto> questionResDto = questionService.getQuestionBoard();
        model.addAttribute("questions", questionResDto);
        return "/pages/index";
    }

    @GetMapping("/questions/{index}")
    public ModelAndView getQuestionDetails(@PathVariable("index") Long index, Model model){
        ModelAndView mav = new ModelAndView();
        QuestionDetailResDto resDto = questionService.getQuestionDetail(index);
        mav.addObject("question",resDto);
        mav.setViewName("/pages/show");
        return mav;
    }

}
