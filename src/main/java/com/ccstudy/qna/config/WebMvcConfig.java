package com.ccstudy.qna.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);


        registry.addViewController("/").setViewName("/home");
        registry.addViewController("/questions/form").setViewName("/content-input2");
        registry.addViewController("/users/form").setViewName("/register");
    }
}