package com.ccstudy.qna.config.auth;


import com.ccstudy.qna.dto.Account.AccountAuthDto;
import com.ccstudy.qna.exception.account.AuthException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Component
public class AuthenticationImpl implements Authentication {

    private static final int ONE_HOUR = 3600;

    //TODO : 옵셔널로 굳이 감쌀 필요가 없음!
    @Override
    public Optional<AccountAuthDto> getAccountAuthDto(NativeWebRequest request) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request.getNativeRequest();
        AccountAuthDto authDto = (AccountAuthDto) httpServletRequest.getSession()
                .getAttribute(AccountAuthDto.ATTRIBUTE_NAME);
        return Optional.ofNullable(authDto);
    }

    @Override
    public Optional<AccountAuthDto> getAccountAuthDto(HttpServletRequest request) {
        AccountAuthDto authDto = (AccountAuthDto) request.getSession()
                .getAttribute(AccountAuthDto.ATTRIBUTE_NAME);
        return Optional.ofNullable(authDto);
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
        //TODO : optional 안티패턴 확인해보기.
        getAccountAuthDto(request)
                .orElseThrow(() -> new AuthException("로그인 되어있지 않습니다."));

        request.getSession().setMaxInactiveInterval(ONE_HOUR);

    }
}
