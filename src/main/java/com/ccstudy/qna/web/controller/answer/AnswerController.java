package com.ccstudy.qna.web.controller.answer;

import com.ccstudy.qna.dto.account.LoginAccount;
import com.ccstudy.qna.dto.answer.AnswerDetailResponseDto;
import com.ccstudy.qna.dto.answer.AnswerSaveRequestDto;
import com.ccstudy.qna.service.answer.AnswerService;
import com.ccstudy.qna.web.auth.Auth;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;

    @GetMapping("/questions/{id}/answers")
    public String answerForm(@PathVariable("id") Long id, @Auth LoginAccount loginAccount, Model model) {
        model.addAttribute("questionId", id);
        model.addAttribute("account", loginAccount);
        return "question/answer/form_create";
    }

    @PostMapping("/question/{id}/answer")
    public String writeAnswer(AnswerSaveRequestDto answerSaveRequestDto, @PathVariable("id") Long id, @Auth LoginAccount loginAccount) {
        answerService.saveAnswer(answerSaveRequestDto, loginAccount, id);
        return "redirect:/questions/" + id;
    }

    @GetMapping("/answer/{id}")
    public String answerDetail(@PathVariable("id") Long id, Model model) {
        AnswerDetailResponseDto answerDetailResponseDto = answerService.getAnswerDetail(id);
        model.addAttribute("answer", answerDetailResponseDto);
        return "question/answer/detail_view";
    }

    @DeleteMapping("/answer/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteAnswer(@PathVariable("id") Long id, @Auth LoginAccount loginAccount) {
        Long questionId = answerService.deleteAnswer(id, loginAccount);
        return new ResponseEntity<>("/questions/" + questionId, HttpStatus.OK);
    }
}
