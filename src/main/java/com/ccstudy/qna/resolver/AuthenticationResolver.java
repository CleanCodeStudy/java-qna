package com.ccstudy.qna.resolver;

import com.ccstudy.qna.auth.Authentication;
import com.ccstudy.qna.auth.AuthenticationImpl;
import com.ccstudy.qna.dto.account.LoginAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Component
public class AuthenticationResolver implements HandlerMethodArgumentResolver {
    private final Authentication authentication;
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        //parameter가 User Type인지 체크
        return parameter.getParameterType().isAssignableFrom(LoginAccount.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return authentication.getLoginUser(webRequest);
    }

}
