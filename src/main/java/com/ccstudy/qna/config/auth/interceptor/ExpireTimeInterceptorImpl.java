package com.ccstudy.qna.config.auth.interceptor;

import com.ccstudy.qna.config.auth.Authentication;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExpireTimeInterceptorImpl implements ExpireTimeInterceptor {

    private static final List<String> MATCH_METHODS = Arrays.asList("POST", "PUT", "DELETE");
    private final Authentication authentication;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String method = request.getMethod();
        if (MATCH_METHODS.contains(method)) {
            authentication.updateAccountExpireTimeAuthDto(request);
        }
    }
}
