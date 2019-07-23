package com.ccstudy.qna.web;

import com.ccstudy.qna.domain.support.AccessUserStore;
import com.ccstudy.qna.service.QuestionService;
import com.ccstudy.qna.service.dto.answer.AnswerRequestDto;
import com.ccstudy.qna.service.dto.question.QuestionRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
        questionService.save(AccessUserStore.getUserId(), requestDto);
        return "redirect:/";
    }

    @PostMapping("/{questionId}/answer")
    public String addAnswer(@PathVariable Long questionId, @Valid AnswerRequestDto requestDto) {
        questionService.addAnswer(AccessUserStore.getUserId(), questionId, requestDto);
        return "redirect:/";
    }

}
