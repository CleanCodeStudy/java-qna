package com.ccstudy.qna.config.resolver;

import com.ccstudy.qna.dto.Account.AccountSessionDto;
import com.ccstudy.qna.exception.account.AuthException;
import org.springframework.context.annotation.Profile;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

@Profile("prod")
@Component
public class AccountHandlerMethodArgumentResolverImpl implements AccountHandlerMethodArgumentResolver {

    private static final String ACCOUNT_ID = "accountId";

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return AccountSessionDto.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        AccountSessionDto accountSessionDto = (AccountSessionDto) request.getSession().getAttribute(ACCOUNT_ID);
        if (accountSessionDto == null) {
            throw new AuthException("로그인 안한 유저입니다");
        }
        return accountSessionDto;
    }
}
