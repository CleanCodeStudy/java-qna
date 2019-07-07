package com.ccstudy.qna.config;

import com.ccstudy.qna.web.interceptor.LoginUserCheckInterceptor;
import com.ccstudy.qna.web.resolver.LoginAccountResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class MvcConfig implements WebMvcConfigurer {

    private final LoginUserCheckInterceptor loginUserCheckInterceptor;

    private final LoginAccountResolver loginAccountResolver;

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
        registry.addInterceptor(loginUserCheckInterceptor)
                .addPathPatterns("/*")
                .excludePathPatterns("/login/**"); //로그인 쪽은 예외처리를 한다.
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(loginAccountResolver);
        WebMvcConfigurer.super.addArgumentResolvers(resolvers);
    }

}

/**
 * Interceptor vs Filter
 * <p>
 * Interceptor
 * <p>
 * * post / after
 * <p>
 * spring 이 필요 없는건 filter layer에서 막아야 함 (웹에서 전반적으로)
 **/