package com.ccstudy.qna.config;

import com.ccstudy.qna.interceptor.QuestionInterceptor;
import com.ccstudy.qna.resolver.AuthenticationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class MvcConfig implements WebMvcConfigurer {

    private final QuestionInterceptor questionInterceptor;

    private final AuthenticationResolver authenticationResolver;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE); // Controller보다 먼저 탄다
        registry.addViewController("/users/registration").setViewName("account/info_create");
        registry.addViewController("/users/updateForm").setViewName("account/info_update");
        registry.addViewController("/login/form").setViewName("account/login");
    }

    //Session을 인자로 박을 수도 있고
    //Formatter도 등록할 수 있고
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(questionInterceptor)
                .addPathPatterns("/*")
                .excludePathPatterns("/login/**"); //로그인 쪽은 예외처리를 한다.
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(authenticationResolver);
        WebMvcConfigurer.super.addArgumentResolvers(resolvers);
    }

}
