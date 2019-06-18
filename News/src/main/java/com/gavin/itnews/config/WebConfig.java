package com.gavin.itnews.config;

import com.gavin.itnews.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by Gavin
 * on 2019/4/11 21:51
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/user/**")
                .addPathPatterns("/msg/**")
                .addPathPatterns("/news/**")
                .addPathPatterns("/addComment/**");
    }

}
