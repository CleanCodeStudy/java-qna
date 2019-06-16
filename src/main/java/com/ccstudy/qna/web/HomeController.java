package com.ccstudy.qna.web;

import com.ccstudy.qna.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {

    private final QuestionService questionService;

    @GetMapping
    public String home(Model model) {
        model.addAttribute(questionService.findAll());
        return "/home";
    }
}
