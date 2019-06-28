package com.ccstudy.qna.advice.common;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Component
public class BaseExceptionModelAndView {

    private static final String ERROR_VIEW_NAME = "/pages/error";
    private static final String ERROR_MESSAGE = "errorMessage";
    private static final String ERROR_CODE = "errorCode";

    public ModelAndView getExceptionModelAndView(String filed, String message, HttpStatus code) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(ERROR_VIEW_NAME);
        modelAndView.addObject(ERROR_MESSAGE, filed + message);
        modelAndView.addObject(ERROR_CODE, code);
        return modelAndView;
    }

}
