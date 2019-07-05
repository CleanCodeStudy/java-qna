package com.ccstudy.qna.config.resolver;

import com.ccstudy.qna.config.auth.Authentication;
import com.ccstudy.qna.dto.Account.AccountAuthDto;
import com.ccstudy.qna.exception.account.AuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

@Profile("prod")
@Component
@RequiredArgsConstructor
public class AccountHandlerMethodArgumentResolverImpl implements AccountHandlerMethodArgumentResolver {

    private final Authentication authentication;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return AccountAuthDto.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        AccountAuthDto accountAuthDto = authentication.getAccountAuthDto(webRequest);
        if (accountAuthDto == null) {
            throw new AuthException("로그인 안한 유저입니다");
        }
        return accountAuthDto;
    }
}
