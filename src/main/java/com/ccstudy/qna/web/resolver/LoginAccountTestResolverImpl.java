package com.ccstudy.qna.web.resolver;

import com.ccstudy.qna.dto.account.LoginAccount;
import com.ccstudy.qna.web.auth.Auth;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

@RequiredArgsConstructor
@Component
@Profile("test")
public class LoginAccountTestResolverImpl implements LoginAccountResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(LoginAccount.class)
                && parameter.hasParameterAnnotation(Auth.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return LoginAccount.createBuilder()
                .email("aaa@google.com")
                .firstName("fn")
                .lastName("ln")
                .build();
    }

}
