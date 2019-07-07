package com.ccstudy.qna.controller;

import com.ccstudy.qna.dto.Account.AccountAuthDto;
import com.ccstudy.qna.dto.Question.QuestionDetailResDto;
import com.ccstudy.qna.dto.Question.QuestionSaveReqDto;
import com.ccstudy.qna.dto.Question.QuestionUpdateReqDto;
import com.ccstudy.qna.service.Checker;
import com.ccstudy.qna.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@Slf4j
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping("")
    public String createQuestion(@Valid QuestionSaveReqDto questionSaveReqDto, AccountAuthDto accountAuthDto) {
        Long questionId = questionService.createQuestion(questionSaveReqDto, accountAuthDto.getId());
        log.info("update question- id : {}", questionId);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String getQuestionDetails(@PathVariable("id") Long id, Model model) {
        QuestionDetailResDto resDto = questionService.getQuestionDetail(id);
        model.addAttribute("question", resDto);
        return "/pages/show";
    }

    @GetMapping("/{id}/form")
    public String getEditFormOfQuestion(@PathVariable("id") Long id, Model model, AccountAuthDto accountAuthDto) {
        Checker.validateAuthorization(id, accountAuthDto);
        QuestionDetailResDto resDto = questionService.getQuestionDetail(accountAuthDto.getId());
        model.addAttribute("question", resDto);
        return "/pages/questionUpdateForm";
    }

    @PutMapping("/{id}")
    public String updateQuestion(@Valid QuestionUpdateReqDto questionUpdateReqDto,
                                 @PathVariable("id") Long id, AccountAuthDto accountAuthDto) {
        Checker.validateAuthorization(id, accountAuthDto);
        Long updatedId = questionService.updateQuestion(questionUpdateReqDto, id);
        log.info("update question- id : " + updatedId);
        return "redirect:/questions/" + updatedId;
    }

    @DeleteMapping("/{id}")
    public String deleteQuestion(@PathVariable("id") Long id, AccountAuthDto accountAuthDto) {
        Checker.validateAuthorization(id, accountAuthDto);
        Long deletedId = questionService.deleteQuestion(id);
        log.info("delete question- id : " + deletedId);
        return "redirect:/";
    }

}
