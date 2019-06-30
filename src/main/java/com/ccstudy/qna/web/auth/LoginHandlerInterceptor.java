package com.ccstudy.qna.web.auth;

import com.ccstudy.qna.domain.support.AccessUserStore;
import com.ccstudy.qna.web.auth.manager.AccessUserManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class LoginHandlerInterceptor implements HandlerInterceptor {

    private final AccessUserManager manager;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        if (AccessUserStore.hasUser() || request.getMethod().equals("PUT")) {
            manager.saveAccessUser(request, AccessUserStore.getUser());
        }
    }


}
