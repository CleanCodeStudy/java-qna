package com.ccstudy.qna.config.resolver;

import com.ccstudy.qna.domain.Account;
import com.ccstudy.qna.dto.Account.AccountAuthDto;
import org.springframework.context.annotation.Profile;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.lang.reflect.Constructor;

@Profile("dev")
@Component
public class AccountTestHandlerMethodArgumentResolverImpl implements AccountHandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return AccountAuthDto.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Account account = Account.createBuilder()
                .build();
        Constructor<Account> testConstructor = Account.class.getDeclaredConstructor(Long.class, Account.class);
        testConstructor.setAccessible(true);
        account = testConstructor.newInstance(1L, account);
        return AccountAuthDto.createBuilder()
                .account(account)
                .build();
    }
}
