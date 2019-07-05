package com.ccstudy.qna.config.auth;

import com.ccstudy.qna.dto.Account.AccountAuthDto;
import org.springframework.web.context.request.NativeWebRequest;

import javax.servlet.http.HttpServletRequest;

public interface Authentication {

    AccountAuthDto getAccountAuthDto(NativeWebRequest request);

    void setAccountAuthDto(HttpServletRequest request, AccountAuthDto accountAuthDto);

    void removeAccountAuthDto(HttpServletRequest request);
}
