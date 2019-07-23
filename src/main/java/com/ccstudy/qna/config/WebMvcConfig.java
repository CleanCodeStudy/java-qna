package com.ccstudy.qna.config;


import com.ccstudy.qna.web.auth.AccessUserHandlerInterceptor;
import com.ccstudy.qna.web.auth.LoginHandlerInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final AccessUserHandlerInterceptor accessUserHandlerInterceptor;

    private final LoginHandlerInterceptor loginHandlerInterceptor;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);

        registry.addViewController("/questions/form").setViewName("/content-input2");
        registry.addViewController("/users/form").setViewName("/register");
        registry.addViewController("/login/form").setViewName("/form");
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(accessUserHandlerInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/login**", "/css/**", "/js/**", "/login/form");

        registry.addInterceptor(loginHandlerInterceptor)
                .addPathPatterns("/login", "/users")
                .excludePathPatterns("/", "/css/**", "/js/**");
    }

}