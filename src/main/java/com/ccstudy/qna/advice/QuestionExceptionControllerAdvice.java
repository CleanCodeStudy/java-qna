package com.ccstudy.qna.advice;

import com.ccstudy.qna.advice.common.BaseExceptionModelAndView;
import com.ccstudy.qna.exception.account.AccountException;
import com.ccstudy.qna.exception.account.QuestionException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@ControllerAdvice
@Slf4j
public class QuestionExceptionControllerAdvice {
    private static final String FIELD = "Question";

    private final BaseExceptionModelAndView baseExceptionModelAndView;

    @ExceptionHandler(QuestionException.class)
    public ModelAndView getErrorOfPasswordException(AccountException e) {
        return baseExceptionModelAndView.getExceptionModelAndView(FIELD + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
