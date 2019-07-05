package com.ccstudy.qna.config.auth.interceptor;

import com.ccstudy.qna.config.auth.Authentication;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class LogoutInterceptorImpl implements LogoutInterceptor {

    private final Authentication authentication;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        authentication.removeAccountAuthDto(request);
    }
}
