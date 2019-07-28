package com.ccstudy.qna.config.auth;


import com.ccstudy.qna.dto.Account.AccountAuthDto;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

@Component
@Profile("dev")
public class AuthenticationTestImpl implements Authentication {

    @Override
    public AccountAuthDto getAccountAuthDto(NativeWebRequest request) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request.getNativeRequest();
        return getAccountAuthDto(httpServletRequest);


    }

    @Override
    public AccountAuthDto getAccountAuthDto(HttpServletRequest request) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Constructor<AccountAuthDto> testConstructor = AccountAuthDto.class.getDeclaredConstructor();
        testConstructor.setAccessible(true);
        AccountAuthDto accountAuthDto = testConstructor.newInstance();
        Field id = accountAuthDto.getClass().getField("id");
        id.set(accountAuthDto, 1L);

        return accountAuthDto;
    }

    @Override
    public void setAccountAuthDto(HttpServletRequest request, AccountAuthDto accountAuthDto) {

    }

    @Override
    public void removeAccountAuthDto(HttpServletRequest request) {

    }

    @Override
    public void updateAccountExpireTimeAuthDto(HttpServletRequest request) {

    }
}
