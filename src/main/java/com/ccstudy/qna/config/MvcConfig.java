package com.ccstudy.qna.config;

import com.ccstudy.qna.interceptor.QuestionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE); // Controller보다 먼저 탄다
        registry.addViewController("/users/registration").setViewName("account/info_create");
        registry.addViewController("/users/updateForm").setViewName("account/info_update");
        registry.addViewController("/login/form").setViewName("account/login");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new QuestionInterceptor())
                .addPathPatterns("/*")
                .excludePathPatterns("/login/**"); //로그인 쪽은 예외처리를 한다.
    }

}
