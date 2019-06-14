package com.ccstudy.qna.controller;

import com.ccstudy.qna.dto.Question.QuestionDetailResDto;
import com.ccstudy.qna.dto.Question.QuestionReqDto;
import com.ccstudy.qna.dto.Question.QuestionResDto;
import com.ccstudy.qna.dto.Question.QuestionUpdateReqDto;
import com.ccstudy.qna.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@Slf4j
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/")
    public String getQuestionBoard(Model model) {
        List<QuestionResDto> questionResDto = questionService.getQuestionBoard();
        model.addAttribute("questions", questionResDto);
        return "/pages/index";
    }

    @PostMapping("/questions")
    public String createQuestion(@ModelAttribute QuestionReqDto questionReqDto) {
        questionService.createQuestion(questionReqDto);
        return "redirect:/";
    }

    @GetMapping("/questions/{id}")
    public String getQuestionDetails(@PathVariable("id") Long id, Model model) {
        QuestionDetailResDto resDto = questionService.getQuestionDetail(id);
        model.addAttribute("question", resDto);
        return "/pages/show";
    }

    @GetMapping("/questions/edit/{id}")
    public String getEditFormOfQuestion(@PathVariable("id") Long id, Model model) {
        QuestionDetailResDto resDto = questionService.getQuestionDetail(id);
        model.addAttribute("question", resDto);
        return "/pages/questionUpdateForm";
    }

    @PutMapping("/questions/{id}")
    public String updateQuestion(@ModelAttribute QuestionUpdateReqDto questionUpdateReqDto, @PathVariable("id") Long id) {
        questionService.updateQuestion(questionUpdateReqDto, id);
        log.info("update question- id : " + id);
        return "redirect:/questions/" + id;
    }

    //log는 어디에서 찍는게 맞는 걸까??
    @DeleteMapping("/questions/{id}")
    public String deleteQuestion(@PathVariable("id") Long id) {
        questionService.deleteQuestion(id);
        log.info("delete question- id : " + id);
        return "redirect:/";
    }


}
