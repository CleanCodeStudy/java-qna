package com.ccstudy.qna.config.auth;


import com.ccstudy.qna.dto.Account.AccountAuthDto;
import com.ccstudy.qna.exception.account.AuthException;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;

import javax.servlet.http.HttpServletRequest;

@Component
@Profile("prod")
public class AuthenticationImpl implements Authentication {

    private static final int ONE_HOUR = 3600;

    @Override
    public AccountAuthDto getAccountAuthDto(NativeWebRequest request) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request.getNativeRequest();
        return getAccountAuthDto(httpServletRequest);
    }

    @Override
    public AccountAuthDto getAccountAuthDto(HttpServletRequest request) {
        AccountAuthDto authDto = (AccountAuthDto) request.getSession()
                .getAttribute(AccountAuthDto.ATTRIBUTE_NAME);
        validateLogin(authDto);
        return authDto;
    }

    @Override
    public void setAccountAuthDto(HttpServletRequest request, AccountAuthDto accountAuthDto) {
        request.getSession()
                .setAttribute(AccountAuthDto.ATTRIBUTE_NAME, accountAuthDto);
        request.getSession()
                .setMaxInactiveInterval(ONE_HOUR);
    }

    @Override
    public void removeAccountAuthDto(HttpServletRequest request) {
        request.getSession()
                .removeAttribute(AccountAuthDto.ATTRIBUTE_NAME);
    }

    @Override
    public void updateAccountExpireTimeAuthDto(HttpServletRequest request) {
        getAccountAuthDto(request);
        request.getSession()
                .setMaxInactiveInterval(ONE_HOUR);
    }

    private void validateLogin(AccountAuthDto accountAuthDto) {
        if (accountAuthDto == null) {
            throw new AuthException(" 로그인 되어있지 않습니다.");
        }
    }
}
