package com.ccstudy.qna.config.auth;

import com.ccstudy.qna.dto.Account.AccountAuthDto;
import org.springframework.web.context.request.NativeWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;

public interface Authentication {

    AccountAuthDto getAccountAuthDto(NativeWebRequest request) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException;

    AccountAuthDto getAccountAuthDto(HttpServletRequest request) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException;

    void setAccountAuthDto(HttpServletRequest request, AccountAuthDto accountAuthDto);

    void removeAccountAuthDto(HttpServletRequest request);

    void updateAccountExpireTimeAuthDto(HttpServletRequest request);
}
