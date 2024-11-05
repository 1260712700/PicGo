package com.jp.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jp.anotation.AccessLimit;
import com.jp.domain.dto.UserLoginDTO;
import com.jp.domain.dto.UserRegisterDTO;
import com.jp.domain.dto.UserResetDTO;
import com.jp.domain.entity.User;
import com.jp.domain.response.ResponseResult;
import com.jp.domain.vo.UserAccountVO;
import com.jp.domain.vo.UserDetailVO;
import com.jp.domain.vo.UserListVO;
import com.jp.mapper.UserMapper;
import com.jp.service.UserService;
import com.jp.utils.SecurityUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author JP
 * @Date 2024/10/30 6:47
 */
@Tag(name="用户接口")
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Resource
    private UserService userService;
    @Operation(summary = "注册")
    @PostMapping("/register")
    public ResponseResult<Void> register(@RequestBody UserRegisterDTO userRegisterDto){
        return userService.register(userRegisterDto);
    }
    @Operation(summary = "通过用户id获取用户数据")
    @GetMapping("/auth/{id}")
    public ResponseResult<UserDetailVO>getUserInfo(@PathVariable String id){
        return userService.getUserInfo(Long.valueOf(id));
    }
    @Operation(summary = "重置密码确认验证码")
    @PostMapping("/reset-confirm")
    public ResponseResult<Void> reset(@RequestBody UserResetDTO userResetDto){
        return userService.resetConfirm(userResetDto);
    }
    @Operation(summary = "重置密码输入密码")
    @PostMapping("/reset-password")
    public ResponseResult<Void> resetConfirm(@RequestBody UserResetDTO userResetDto){
        return userService.resetPassword(userResetDto);
    }
    @Resource
    private UserMapper userMapper;
    @PreAuthorize("hasAnyAuthority('system:user:list')")
    @Operation(summary = "获取用户列表")
    @AccessLimit(seconds = 60, maxCount = 30)
    @GetMapping("/list")
    public ResponseResult<List<UserListVO>> getUserList() {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        List<UserListVO> userListVOS = userMapper.selectList(wrapper).stream().map(user -> user.asViewObject(UserListVO.class)).toList();
        return ResponseResult.success(userListVOS);
    }
    @Operation(summary = "获取当前登录用户信息")
    @AccessLimit(seconds = 60, maxCount = 30)
    @GetMapping("/auth/info")
    public ResponseResult<UserAccountVO> getInfo() {
        return  userService.findAccountById(SecurityUtil.getUserId());
    }
}
