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

     @Override
       public void addCorsMappings(CorsRegistry registry) {
         registry.addMapping("/**") // 所有接口
//                 .allowedOrigins("http://18.208.189.107")
//                 .allowedOrigins("") //设置允许跨域请求的域名
                .allowCredentials(true) // 是否发送 Cookie
         .allowedOriginPatterns("*") // 支持域
        .allowedMethods("GET", "POST", "PUT", "DELETE","") // 支持方法
        .allowedHeaders("*")
    .allowedOrigins("http://192.168.50.209","http://18.208.189.107")
          .exposedHeaders("*");
     }
}
