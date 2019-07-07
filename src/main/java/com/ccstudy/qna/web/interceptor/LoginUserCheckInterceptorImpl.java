package com.ccstudy.qna.web.interceptor;

import com.ccstudy.qna.web.auth.LoginAccountSessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
@RequiredArgsConstructor
@Profile("dev")
public class LoginUserCheckInterceptorImpl implements LoginUserCheckInterceptor {

    private final LoginAccountSessionManager loginAccountSessionManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //TODO: request로 메서드 확인
        //request.getMethod()
        if (loginAccountSessionManager.hasAuthentication(request)) {
            return true;
        }
        response.sendRedirect("/login/form");
        return false;
    }

}
