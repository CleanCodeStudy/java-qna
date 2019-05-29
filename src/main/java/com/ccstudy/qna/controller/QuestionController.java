package com.ccstudy.qna.controller;


import com.ccstudy.qna.dto.QuestionSaveRequestDto;
import com.ccstudy.qna.service.QuestionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
@Slf4j
public class QuestionController {
    private QuestionService questionService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("questions", questionService.showQuestions());
        return "index";
    }

    @PostMapping("/questions")
    public String saveQuestion(QuestionSaveRequestDto dto) {
        questionService.save(dto);
        return "redirect:/";
    }

    @GetMapping("/questions/{index}")
    public String showQuestionDetail(@PathVariable("index") int index, Model model) {
        model.addAttribute("question", questionService.showQuestionDetail(index));
        return "cards1";
    }

}
