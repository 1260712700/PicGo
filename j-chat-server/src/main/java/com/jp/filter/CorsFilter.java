package com.jp.filter;

import com.jp.constants.Constant;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 解决跨域问题
 * @Author JP
 * @Date 2024/10/30 7:37
 */
@Component
@Order(Constant.ORDER_CORS)
public class CorsFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        this.addCorsHeader(request, response);
        chain.doFilter(request,response);
    }
    private void addCorsHeader(HttpServletRequest request, HttpServletResponse response){
        response.addHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.addHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
        response.addHeader("Access-Control-Allow-Headers", "Authorization,Content-Type,X-Client-Type,x-requested-with");
    }
}
