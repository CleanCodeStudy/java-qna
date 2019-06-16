package com.ccstudy.qna.interceptor;

import com.ccstudy.qna.dto.account.AccountSessionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@Component
public class QuestionInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        AccountSessionDto loginUserEmail = (AccountSessionDto) session.getAttribute("LOGIN_ACCOUNT");

        if(ObjectUtils.isEmpty(loginUserEmail)){
            response.sendRedirect("/login/form");
            return false;
        }
        session.setMaxInactiveInterval(30*60);
        return true;
    }
}
