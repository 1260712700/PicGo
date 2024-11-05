package com.jp.config;

import com.jp.constants.SecurityConstant;
import com.jp.filter.CorsFilter;
import com.jp.filter.JwtFilter;
import com.jp.handler.SecurityHandler;
import jakarta.annotation.Resource;
import jakarta.servlet.Filter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextHolderFilter;
import org.springframework.security.web.session.DisableEncodeUrlFilter;


/**
 * @Author JP
 * @Date 2024/10/31 2:20
 */
@Configuration
public class SecurityConfig  {
    //密码编码器
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Resource
    private SecurityHandler securityHandler;
    @Resource
    private JwtFilter jwtFilter;

        @Bean
    public SecurityFilterChain publicApiFilterChain(HttpSecurity http) throws Exception {
            return http
                    .authorizeHttpRequests(conf -> conf
                            // 需要校验的接口
                            .requestMatchers(SecurityConstant.AUTH_CHECK_ARRAY).authenticated()
                            // 注册接口
                            // 其他的都不用要校验
                            .anyRequest().permitAll()
                    )
                    .formLogin(conf -> conf
                            // 登录页面
                            .loginProcessingUrl(SecurityConstant.LOGIN_PAGE)
                            // 成功登录处理
                            .successHandler(securityHandler::onAuthenticationSuccess)
                            // 失败登录处理
                            .failureHandler(securityHandler::onAuthenticationFailure)
                    )
                    .logout(conf -> conf
                            // 登出页面
                            .logoutUrl(SecurityConstant.LOGOUT_PAGE)
                            // 退出登录处理
                            .logoutSuccessHandler(securityHandler::onLogoutSuccess)
                    )
                    .exceptionHandling(conf -> conf
                            // 没有登录处理
                            .authenticationEntryPoint(securityHandler::onUnAuthenticated)
                            // 没有权限处理
                            .accessDeniedHandler(securityHandler::onAccessDeny)
                    )
                    // 禁用 csrf
                    .csrf(AbstractHttpConfigurer::disable)
                    // 不处理 session ，使用token
                    .sessionManagement(conf -> conf.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    // token 校验添加过滤器
                    .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                    .build();
    }

}
