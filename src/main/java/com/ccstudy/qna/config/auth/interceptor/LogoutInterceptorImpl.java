package com.ccstudy.qna.config.auth.interceptor;

import com.ccstudy.qna.config.auth.Authentication;
import com.ccstudy.qna.exception.account.AuthException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
@Slf4j
public class LogoutInterceptorImpl implements LogoutInterceptor {

    private final Authentication authentication;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        Long sessionId = authentication.getAccountAuthDto(request)
                .orElseThrow(() -> new AuthException("로그아웃을 하려면 로그인을 해야합니다."))
                .getId();

        log.info("logout id : {}", sessionId);

        authentication.removeAccountAuthDto(request);
    }
}
