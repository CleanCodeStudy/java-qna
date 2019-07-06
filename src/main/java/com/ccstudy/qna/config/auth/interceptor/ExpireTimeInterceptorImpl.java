package com.ccstudy.qna.config.auth.interceptor;

import com.ccstudy.qna.config.auth.Authentication;
import com.ccstudy.qna.dto.Account.AccountAuthDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExpireTimeInterceptorImpl implements ExpireTimeInterceptor {

    private final Authentication authentication;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        authentication.updateAccountExpireTimeAuthDto(request);
    }
}
