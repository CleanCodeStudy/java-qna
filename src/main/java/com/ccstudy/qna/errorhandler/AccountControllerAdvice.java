package com.ccstudy.qna.errorhandler;

import com.ccstudy.qna.error.UnAuthenticationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(annotations = Controller.class)
@Slf4j
public class AccountControllerAdvice {

    @ExceptionHandler(value = UnAuthenticationException.class)
    public String unAuthentication() {
        log.info("UnAuthenticationException is happened!");
        return "redirect:/?error";
    }
}
