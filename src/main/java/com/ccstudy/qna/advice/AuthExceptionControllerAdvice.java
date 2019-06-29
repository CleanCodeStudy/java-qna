package com.ccstudy.qna.advice;

import com.ccstudy.qna.advice.common.BaseExceptionModelAndView;
import com.ccstudy.qna.exception.id.AuthException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class AuthExceptionControllerAdvice {

    private static final String FIELD = "ID";

    private final BaseExceptionModelAndView baseExceptionModelAndView;

    @ExceptionHandler(AuthException.class)
    public ModelAndView getErrorPage(AuthException e) {
        return baseExceptionModelAndView.getExceptionModelAndView(FIELD + e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

}
