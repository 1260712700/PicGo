package com.jp.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.jp.domain.entity.LoginUser;
import com.jp.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * todo 添加jwt
 * jwt过滤器
 * @Author JP
 * @Date 2024/10/31 6:07
 */
@Component
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 提取 Header
        String authorization = request.getHeader("Authorization");
        // 解析jwt
        DecodedJWT jwt = jwtUtil.resolveJwt(authorization);
        if (!ObjectUtils.isEmpty(jwt)) {
            // 获取UserDetails
            LoginUser user = (LoginUser) jwtUtil.toUser(jwt);
            // 创建认证对象
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            // 保存认证详细信息
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            // 验证通过，设置上下文中
            SecurityContextHolder.getContext().setAuthentication(authentication);

        }
        filterChain.doFilter(request, response);
    }
}
