package com.ccstudy.qna.web;

import com.ccstudy.qna.exception.UnAuthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class WebControllerAdvice {

    @ExceptionHandler(UnAuthorizedException.class)
    public String unAuthorizedException(Exception ex, Model model) {
        model.addAttribute("exception", ex);
        return "/form";
    }
}
