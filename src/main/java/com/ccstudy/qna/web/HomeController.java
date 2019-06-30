package com.ccstudy.qna.web;

import com.ccstudy.qna.service.QuestionService;
import com.ccstudy.qna.service.UserService;
import com.ccstudy.qna.service.dto.user.LoginDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {

    private final QuestionService questionService;

    private final UserService userService;

    @GetMapping
    public String home(Model model) {
        model.addAttribute("questions", questionService.findAll());
        return "home";
    }

    @PostMapping("login")
    public String login(@Valid LoginDto loginDto) {
        userService.login(loginDto);

        return "redirect:/";
    }
}
