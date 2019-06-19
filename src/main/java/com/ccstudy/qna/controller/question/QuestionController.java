package com.ccstudy.qna.controller.question;


import com.ccstudy.qna.dto.account.Auth;
import com.ccstudy.qna.dto.account.LoginAccount;
import com.ccstudy.qna.dto.question.QuestionSaveRequestDto;
import com.ccstudy.qna.dto.question.QuestionUpdateRequestDto;
import com.ccstudy.qna.service.question.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@Slf4j
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/")
    public String index(@Auth LoginAccount loginAccount, Model model) {
        log.info(loginAccount.toString());
        model.addAttribute("questions", questionService.showQuestions());
        return "index";
    }

    @GetMapping("/questions/form")
    public String writeForm(Model model, HttpSession httpSession) {
        model.addAttribute("account", httpSession.getAttribute("LOGIN_ACCOUNT"));
        return "question/form_create";
    }


    @PostMapping("/questions")
    public String saveQuestion(@Auth LoginAccount loginAccount, QuestionSaveRequestDto dto, HttpSession httpSession) {
        questionService.save(dto, httpSession);
        return "redirect:/";
    }

    @GetMapping("/questions/{id}")
    public String showQuestionDetail(@PathVariable("id") Long id, Model model) {
        model.addAttribute("question", questionService.showQuestionDetail(id));
        return "question/detail_view";
    }

    @GetMapping("/question/updateForm/{id}")
    public String questionUpdateForm(@PathVariable("id") Long id, Model model){
        model.addAttribute("question", questionService.showQuestionDetail(id));
        return "question/form_update";
    }

    @PutMapping("/questions/{id}")
    public String update(QuestionUpdateRequestDto dto, HttpSession httpSession) {
        Long updateId = questionService.updateQuestion(dto, httpSession);
        return "redirect:/questions/"+updateId;
    }
    @DeleteMapping("/questions/{id}")
    public String delete(@PathVariable("id") Long id, HttpSession httpSession) {
        questionService.delete(id, httpSession);
        return "redirect:/";
    }

}
