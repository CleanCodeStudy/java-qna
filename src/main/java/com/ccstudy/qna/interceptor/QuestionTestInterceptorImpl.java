package com.ccstudy.qna.interceptor;

import com.ccstudy.qna.auth.Authentication;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
@Profile("test")
public class QuestionTestInterceptorImpl implements QuestionInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }
//LoginAccount loginAccountEmail = authentication.getLoginUser(request);

}
