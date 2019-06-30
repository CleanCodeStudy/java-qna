package com.ccstudy.qna.interceptor;

import com.ccstudy.qna.auth.Authentication;
import com.ccstudy.qna.dto.account.LoginAccount;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
@RequiredArgsConstructor
@Profile("dev")
public class QuestionInterceptorImpl implements QuestionInterceptor {

    private final Authentication authentication;


    //interface 쓰는게 더 좋음
    //test 코드 작성을 못함
    //interceptor를 두개 만들고 하나는 무조건 TRUE로 반환하게 해서 두개 다 등록하고 쓰는게 일반적

    //request header에 test 추가해서 -> test가 있으면 무조건 TRUe를 반환하도록 짜면 테스트코드에 써먹을 수 있음
    //빈으로 만들고 PROfile별로 빈 주입하는 방식으로 테스트와 개발 환경
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        LoginAccount loginAccountEmail = authentication.getLoginUser(request);

        if(ObjectUtils.isEmpty(loginAccountEmail)){
            response.sendRedirect("/login/form");
            return false;
        }
        //session.setMaxInactiveInterval(30*60);

        return true;
    }
//LoginAccount loginAccountEmail = authentication.getLoginUser(request);

}
