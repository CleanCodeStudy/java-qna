package com.ccstudy.qna.web.auth;

import com.ccstudy.qna.dto.account.LoginAccount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.NoSuchElementException;

@Component
@Slf4j
public class LoginAccountSessionManagerImpl implements LoginAccountSessionManager {

    private static String USER_SESSION_KEY = "LOGIN_ACCOUNT";

    private static int SESSION_INTERVAL = 30 * 60;

    @Override
    public boolean hasAuthentication(HttpServletRequest httpServletRequest) throws Exception {
        if (httpServletRequest.getSession().getAttribute(USER_SESSION_KEY) != null) {
            return true;
        }
        return false;
    }

    @Override
    public LoginAccount getLoginAccount(NativeWebRequest nativeWebRequest) throws Exception {
        LoginAccount loginAccount = (LoginAccount) nativeWebRequest.getAttribute(USER_SESSION_KEY, WebRequest.SCOPE_SESSION);
        if (loginAccount == null) {
            throw new NoSuchElementException("로그인이 안되어있음");
        }
        return loginAccount;
    }

    @Override
    public HttpSession saveSession(HttpServletRequest httpServletRequest, LoginAccount loginAccount) {
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute(USER_SESSION_KEY, loginAccount);
        session.setMaxInactiveInterval(SESSION_INTERVAL);

        log.info("사용자 {}의 세션을 저장합니다.", loginAccount.getEmail());
        return session;
    }

    @Override
    public void deleteSession(HttpServletRequest httpServletRequest) {
        HttpSession httpSession = httpServletRequest.getSession();
        httpSession.removeAttribute(USER_SESSION_KEY);
    }
}
