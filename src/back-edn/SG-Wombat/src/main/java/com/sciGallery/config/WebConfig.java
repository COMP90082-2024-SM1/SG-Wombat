package com.sciGallery.config;

import com.sciGallery.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private  LoginInterceptor loginInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/login");
    }

    // @Override
    //   public void addCorsMappings(CorsRegistry registry) {
    //     registry.addMapping("/**") // 所有接口
    //            .allowCredentials(true) // 是否发送 Cookie
    //     .allowedOriginPatterns("*") // 支持域
    //    .allowedMethods("GET", "POST", "PUT", "DELETE","") // 支持方法
    //    .allowedHeaders("*")
    //.allowedOrigins("*")
    //      .exposedHeaders("*");
    // }
}
