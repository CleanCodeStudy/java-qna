package com.ccstudy.qna.config.resolver;

import com.ccstudy.qna.exception.NotLoginUserException;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;


public class AccountHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    public static final String ACCOUNT_ID = "AccountId";

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return Long.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        Long loginAccountId = (Long) request.getSession().getAttribute(ACCOUNT_ID);
        if (loginAccountId == null) {
            throw new NotLoginUserException("로그인 안한 유저입니다");
        }
        return loginAccountId;
    }
}
