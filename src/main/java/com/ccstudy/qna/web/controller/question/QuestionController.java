package com.ccstudy.qna.web.controller.question;


import com.ccstudy.qna.dto.account.LoginAccount;
import com.ccstudy.qna.dto.question.QuestionSaveRequestDto;
import com.ccstudy.qna.dto.question.QuestionUpdateRequestDto;
import com.ccstudy.qna.service.question.QuestionService;
import com.ccstudy.qna.web.auth.Auth;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String writeForm(@Auth LoginAccount loginAccount,  Model model) {
        model.addAttribute("account", loginAccount);
        return "question/form_create";
    }


    @PostMapping("/questions")
    public String saveQuestion(QuestionSaveRequestDto dto, @Auth LoginAccount loginAccount) {
        questionService.save(dto, loginAccount);
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
    public String update(QuestionUpdateRequestDto dto, @Auth LoginAccount loginAccount) {
        Long updateId = questionService.updateQuestion(dto, loginAccount);
        return "redirect:/questions/"+updateId;
    }
    @DeleteMapping("/questions/{id}")
    public String delete(@PathVariable("id") Long id, @Auth LoginAccount loginAccount) {
        questionService.delete(id, loginAccount);
        return "redirect:/";
    }

}
