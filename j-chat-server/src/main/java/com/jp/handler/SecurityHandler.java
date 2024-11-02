package com.jp.handler;

import cn.hutool.core.util.StrUtil;
import com.jp.constants.Constant;
import com.jp.constants.RespConstant;
import com.jp.domain.entity.LoginUser;
import com.jp.domain.response.ResponseResult;
import com.jp.domain.vo.AuthorizeVO;
import com.jp.enums.RespEnum;
import com.jp.utils.JwtUtil;
import com.jp.utils.WebUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.util.UUID;

/**
 * security处理器
 */
@Component
@Slf4j
public class SecurityHandler {

    @Resource
    private JwtUtil jwtUtil;
    /**
     * 登录成功处理
     *
     * @param request        请求
     * @param response       响应
     * @param authentication 认证信息
     */
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) {
        handlerOnAuthenticationSuccess(request,response,(LoginUser)authentication.getPrincipal());
    }

    public void handlerOnAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            LoginUser user
    ) {
//        登录成功
        String typeHeader = request.getHeader(Constant.TYPE_HEADER);
        if ((StrUtil.equalsAny(typeHeader,Constant.BACKEND_REQUEST,Constant.FRONTEND_REQUEST)
         && user.getUser().getRegisterType() == 1)) {
            throw new BadCredentialsException("非法请求");
        }
        Long id = user.getUser().getId();
        String name = user.getUser().getUsername();
        // UUID做jwt的id
        String uuid = UUID.randomUUID().toString();
        // 生成jwt
        String token = jwtUtil.createJwt(uuid, user, id, name);

        // 转换VO
        AuthorizeVO authorizeVO = user.getUser().asViewObject(AuthorizeVO.class, v -> {
            v.setToken(token);
            v.setExpire(jwtUtil.expireTime());
        });
        // TODO 更新登录状态
//        userService.userLoginStatus(user.getUser().getId());
        // TODO: 2024/11/1   登录成功日志
        WebUtil.renderString(response, ResponseResult.success(authorizeVO, RespConstant.SUCCESS_LOGIN_MSG).asJsonString());
    }

    /**
     * 退出登录处理
     */
    public void onLogoutSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) {
        boolean invalidateJwt = jwtUtil.invalidateJwt(request.getHeader("Authorization"));
        if (invalidateJwt) {
            WebUtil.renderString(response, ResponseResult.success().asJsonString());
            return;
        }
        WebUtil.renderString(response, ResponseResult.failure(RespEnum.NOT_LOGIN.getCode(), RespEnum.NOT_LOGIN.getMsg()).asJsonString());
    }


    /**
     * 登录失败处理
     */
    public void onAuthenticationFailure(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception
    ) throws IOException {
        log.error("登录失败"+exception.getMessage());
        WebUtil.renderString(response, ResponseResult.failure(RespEnum.USERNAME_OR_PASSWORD_ERROR.getCode(), exception.getMessage()).asJsonString());
    }

    /**
     * 没有登录处理
     */
    public void onUnAuthenticated(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        log.error(RespEnum.NOT_LOGIN.getMsg()+exception.getMessage());
        WebUtil.renderString(response, ResponseResult.failure(RespEnum.NOT_LOGIN.getCode(), RespEnum.NOT_LOGIN.getMsg()).asJsonString());
    }
    /**
     * 没有权限处理
     */
    public void onAccessDeny(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception) {
        log.error(RespEnum.NO_PERMISSION.getMsg()+exception.getMessage());
        WebUtil.renderString(response, ResponseResult.failure(RespEnum.NO_PERMISSION.getCode(), RespEnum.NO_PERMISSION.getMsg()).asJsonString());
    }

}
