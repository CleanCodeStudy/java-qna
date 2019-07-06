package com.ccstudy.qna.controller;

import com.ccstudy.qna.dto.Account.AccountAuthDto;
import com.ccstudy.qna.dto.Account.AccountSaveReqDto;
import com.ccstudy.qna.dto.Answer.AnswerSaveReqDto;
import com.ccstudy.qna.service.AnswerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AnswerController {

    private final AnswerService answerService;

    @PostMapping("/questions/{questionId}/answers")
    public String saveAnswer(@Valid AnswerSaveReqDto saveReqDto, @PathVariable(name="questionId") Long questionId,
                             @Valid AccountAuthDto accountAuthDto) {
        Long answerId = answerService.createAnswer(saveReqDto, accountAuthDto.getId(), questionId);
        log.info("save answer: {}", answerId);
        return "redirect:/users";
    }

}
