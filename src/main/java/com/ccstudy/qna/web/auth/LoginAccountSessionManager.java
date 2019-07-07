package com.ccstudy.qna.web.auth;

import com.ccstudy.qna.dto.account.LoginAccount;
import org.springframework.web.context.request.NativeWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface LoginAccountSessionManager {
    boolean hasAuthentication(HttpServletRequest httpServletRequest) throws Exception;

    LoginAccount getLoginAccount(NativeWebRequest nativeWebRequest) throws Exception;

    HttpSession saveSession(HttpServletRequest httpServletRequest, LoginAccount accountLoginRequestDto);

    void deleteSession(HttpServletRequest httpServletRequest);
}
