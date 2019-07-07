package com.ccstudy.qna.web.resolver;

import com.ccstudy.qna.dto.account.LoginAccount;
import com.ccstudy.qna.web.auth.Auth;
import com.ccstudy.qna.web.auth.LoginAccountSessionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

@RequiredArgsConstructor
@Component
@Profile("dev")
public class LoginAccountResolverImpl implements LoginAccountResolver {
    private final LoginAccountSessionManager loginAccountSessionManager;
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(LoginAccount.class)
                && parameter.hasParameterAnnotation(Auth.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return loginAccountSessionManager.getLoginAccount(webRequest);
    }

}
