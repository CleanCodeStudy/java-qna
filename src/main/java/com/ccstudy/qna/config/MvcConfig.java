package com.ccstudy.qna.config;

import com.ccstudy.qna.config.auth.interceptor.ExpireTimeInterceptor;
import com.ccstudy.qna.config.auth.interceptor.LoginInterceptor;
import com.ccstudy.qna.config.auth.interceptor.LogoutInterceptor;
import com.ccstudy.qna.config.resolver.AccountHandlerMethodArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class MvcConfig implements WebMvcConfigurer {


    private static final List<String> EXPIRED_ADD_PATTERNS = Arrays.asList("/questions/**", "/users/**");
    private static final List<String> EXPIRED_EXCLUDE_PATTERNS = Arrays.asList("/users/login", "/users/logout");

    private final AccountHandlerMethodArgumentResolver accountHandlerMethodArgumentResolver;
    private final LoginInterceptor loginInterceptor;
    private final LogoutInterceptor logoutInterceptor;
    private final ExpireTimeInterceptor expireTimeInterceptor;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE); //controller 랑 여기서 매핑하는데, MVC Config가 제일 높은 순위로!
        registry.addViewController("/question/form").setViewName("/pages/form");
        registry.addViewController("/register").setViewName("/pages/register");
        registry.addViewController("/login").setViewName("/pages/login");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(accountHandlerMethodArgumentResolver);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/users/login");

        registry.addInterceptor(logoutInterceptor)
                .addPathPatterns("/users/logout");

        registry.addInterceptor(expireTimeInterceptor)
                .addPathPatterns(EXPIRED_ADD_PATTERNS)
                .excludePathPatterns(EXPIRED_EXCLUDE_PATTERNS);
    }
}
