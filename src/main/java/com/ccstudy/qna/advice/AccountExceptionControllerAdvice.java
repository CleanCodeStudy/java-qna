package com.ccstudy.qna.advice;

import com.ccstudy.qna.exception.PasswordException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice
public class AccountExceptionControllerAdvice {
    private static final String FIELD = "Account";

    @ExceptionHandler(PasswordException.class)
    public ModelAndView getErrorPage(PasswordException passwordException) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/pages/error");
        modelAndView.addObject("errorMessage", FIELD + passwordException.getMessage());
        modelAndView.addObject("errorCode", HttpStatus.BAD_REQUEST);
        return modelAndView;
    }

}
