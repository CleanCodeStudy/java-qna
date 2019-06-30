package com.ccstudy.qna.web.auth;

import com.ccstudy.qna.domain.support.AccessUser;
import com.ccstudy.qna.domain.support.AccessUserStore;
import com.ccstudy.qna.web.auth.manager.AccessUserManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class AccessUserHandlerInterceptor implements HandlerInterceptor {

    private static final String REDIRECT_URL = "/login/form";
    private final AccessUserManager manager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        AccessUser accessUser = manager.getAccessUser(request);

        if (ObjectUtils.isEmpty(accessUser)) {
            response.sendRedirect(REDIRECT_URL);
            return false;
        }

        AccessUserStore.setAccessUser(accessUser);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        AccessUserStore.clear();
    }


}
