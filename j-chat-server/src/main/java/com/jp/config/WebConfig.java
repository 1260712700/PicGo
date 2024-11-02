package com.jp.config;

import com.jp.handler.AccessLimitInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author JP
 * @Date 2024/10/31 0:35
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Resource
    private AccessLimitInterceptor accessLimitInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        注册redis限流器
        registry.addInterceptor(accessLimitInterceptor).addPathPatterns("/**");
    }
}
