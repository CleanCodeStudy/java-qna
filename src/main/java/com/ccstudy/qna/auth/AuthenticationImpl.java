package com.ccstudy.qna.auth;

import com.ccstudy.qna.dto.account.LoginAccount;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.NoSuchElementException;

@Component
public class AuthenticationImpl implements Authentication {
    @Override
    public LoginAccount getLoginUser(NativeWebRequest nativeWebRequest) {
        LoginAccount loginAccount = (LoginAccount)nativeWebRequest.getAttribute("LOGIN_ACCOUNT", WebRequest.SCOPE_SESSION);
        if(loginAccount == null){
            throw new NoSuchElementException("로그인이 안되어있음");
        }
        return loginAccount;
    }

    @Override
    public LoginAccount getLoginUser(HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        LoginAccount loginAccount = (LoginAccount) session.getAttribute("LOGIN_ACCOUNT");
        session.setMaxInactiveInterval(30*60);
        return loginAccount;
    }
}
