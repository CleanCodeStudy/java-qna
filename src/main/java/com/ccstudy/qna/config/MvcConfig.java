package com.ccstudy.qna.config;

import com.ccstudy.qna.config.resolver.AccountHandlerMethodArgumentResolver;
import com.ccstudy.qna.interceptor.LoginSessionCheckInterceptor;
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

    private final AccountHandlerMethodArgumentResolver accountHandlerMethodArgumentResolver;

    //이게 있으면 에러가 뜬다;;;;;;;;;;;;
    private final LoginSessionCheckInterceptor loginSessionCheckInterceptor;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE); //controller 랑 여기서 매핑하는데, MVC Config가 제일 높은 순위로!
        registry.addViewController("/question/form").setViewName("/pages/form");
        registry.addViewController("/register").setViewName("/pages/register");
        registry.addViewController("/login").setViewName("/pages/login");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginSessionCheckInterceptor)
                .addPathPatterns("/users/{id}")
                .addPathPatterns("/questions/edit/**")
                .addPathPatterns("/questions/delete/**")
                .excludePathPatterns("/users/login");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(accountHandlerMethodArgumentResolver);
    }
}
