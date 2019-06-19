package com.ccstudy.qna.interceptor;

import com.ccstudy.qna.dto.account.LoginAccount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@Component
public class QuestionInterceptor implements HandlerInterceptor {
    //interface 쓰는게 더 좋음
    //test 코드 작성을 못함
    //interceptor를 두개 만들고 하나는 무조건 TRUE로 반환하게 해서 두개 다 등록하고 쓰는게 일반적

    //request header에 test 추가해서 -> test가 있으면 무조건 TRUe를 반환하도록 짜면 테스트코드에 써먹을 수 있음
    //빈으로 만들고 PROfile별로 빈 주입하는 방식으로 테스트와 개발 환경
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        LoginAccount loginAccountEmail = (LoginAccount) session.getAttribute("LOGIN_ACCOUNT");

        if(ObjectUtils.isEmpty(loginAccountEmail)){
            response.sendRedirect("/login/form");
            return false;
        }
        session.setMaxInactiveInterval(30*60);

        return true;
    }


}
