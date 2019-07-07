package com.ccstudy.qna.config.auth.interceptor;

import com.ccstudy.qna.config.auth.Authentication;
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

        Long sessionId = authentication.getAccountAuthDto(request).getId();

        log.info("logout id : {}", sessionId);

        authentication.removeAccountAuthDto(request);
    }
}
