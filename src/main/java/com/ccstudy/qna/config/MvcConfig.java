package com.ccstudy.qna.config;

import com.ccstudy.qna.config.resolver.AccountHandlerMethodArgumentResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class MvcConfig implements WebMvcConfigurer {


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE); //controller 랑 여기서 매핑하는데, MVC Config가 제일 높은 순위로!
        registry.addViewController("/question/form").setViewName("/pages/form");
        registry.addViewController("/register").setViewName("/pages/register");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(handlerInterceptor());
    }

    @Bean
    public HandlerMethodArgumentResolver handlerInterceptor() {
        return new AccountHandlerMethodArgumentResolver();
    }

    //내부클래스
}
