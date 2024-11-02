package com.jp.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jp.constants.*;
import com.jp.domain.entity.LoginUser;
import com.jp.domain.dto.UserRegisterDto;
import com.jp.domain.dto.UserResetDto;
import com.jp.domain.entity.*;
import com.jp.domain.response.ResponseResult;
import com.jp.domain.vo.UserAccountVO;
import com.jp.domain.vo.UserDetailVo;
import com.jp.enums.RespEnum;
import com.jp.enums.RoleEnum;
import com.jp.mapper.*;
import com.jp.service.UserService;
import com.jp.utils.AddressUtil;
import com.jp.utils.IpUtil;
import com.jp.utils.RedisCache;
import com.jp.utils.SecurityUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
* @author 12607
* @description 针对表【j_user】的数据库操作Service实现
* @createDate 2024-10-30 05:13:35
*/
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
    @Resource
    private RedisCache redisCache;
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private RolePermissionMapper rolePermissionMapper;
    @Resource
    private PermissionMapper permissionMapper;
    @Resource
    private PasswordEncoder passwordEncoder;
@Override
public  ResponseResult<UserAccountVO> findAccountById(Long id) {
    User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getId, id));
    List<String> userRoles = SecurityUtil.getUserRoles();
    // 角色
    List<String> roles = new ArrayList<>();
    // 权限
    List<String> permissions = new ArrayList<>();
    userRoles.forEach(role -> {
        if (role.startsWith(SecurityConstant.ROLE_PREFIX)) {
            // 去掉前缀，添加
            roles.add(role.substring(SecurityConstant.ROLE_PREFIX.length()));
        } else {
            permissions.add(role);
        }
    });
    UserAccountVO userAccountVO = user.asViewObject(UserAccountVO.class, role -> {
        role.setRoles(roles);
        role.setPermissions(permissions);
    });
    return ResponseResult.success(userAccountVO);
}
    /**
     * spring security安全处理
     * @param username 用户名
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals("")) {
            throw new BadCredentialsException(RespConstant.USERNAME_OR_PASSWORD_ERROR_MSG);
        }
        HttpServletRequest request = SecurityUtil.getCurrentHttpRequest();
        String equipmentHeader = null;
        if (request != null) {
            equipmentHeader = request.getHeader(Constant.TYPE_HEADER);
        }
        /**
         * todo 第三方登录
         */
           User user = findAccountByNameOrEmail(username);
        // 2. 判断用户是否存在
        if (ObjectUtils.isEmpty(user)) {
            // 不存在，抛出异常
            throw new UsernameNotFoundException(RespConstant.USERNAME_OR_PASSWORD_ERROR_MSG);
        }
        return handlerLogin(user, equipmentHeader);
    }
//    处理登录
    public LoginUser handlerLogin(User user, String equipmentHeader) {
        HttpServletRequest request = SecurityUtil.getCurrentHttpRequest();
//        判断后台请求还是前台请求
        String header = null;
        if (request != null) {
            header = request.getHeader(Constant.TYPE_HEADER);
        }
        // 查询用户角色
        List<UserRole> userRoles = userRoleMapper.selectList(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId, user.getId()));
        List<Role> roles = userRoles.stream().map(role -> roleMapper.selectById(role.getRoleId())).
                filter(role -> Objects.equals(role.getStatus(), RoleEnum.Role_STATUS_ARTICLE.getStatus())).toList();
        // 用户是否被禁用
        if (user.getIsDisable() == 1) {
            throw new BadCredentialsException(RespConstant.ACCOUNT_DISABLED_MSG);
        }
        // 是否测试账号前台
        if (header == null || (roles.stream().anyMatch(role -> role.getRoleKey().equals(SecurityConstant.ROLE_TESTER)) && !header.equals(Constant.BACKEND_REQUEST))) {
            throw new BadCredentialsException(RespConstant.TEST_ACCOUNT_MSG);
        }
        // 判断用户是否具备任何权限,
        if ((equipmentHeader != null && equipmentHeader.equals(Constant.BACKEND_REQUEST) && ObjectUtils.isEmpty(roles))) {
            throw new BadCredentialsException(RespConstant.NO_PERMISSION_MSG);
        }
        if (!roles.isEmpty()) {
            // 查询权限关系表
            LambdaQueryWrapper<RolePermission>lqw=new LambdaQueryWrapper<>();
            lqw.in(RolePermission::getRoleId,roles.stream().map(Role::getId).toList());
            List<RolePermission> rolePermissions = rolePermissionMapper.selectList(lqw);
            // 查询角色权限
            List<Long> pIds = rolePermissions.stream().map(RolePermission::getPermissionId).toList();
            List<Permission> permissions = permissionMapper.selectBatchIds(pIds);
            // 组合角色，权限
            List<String> list = permissions.stream().map(Permission::getPermissionKey).collect(Collectors.toList());
            roles.forEach(role -> list.add(SecurityConstant.ROLE_PREFIX + role.getRoleKey()));//组合成: ROLE_角色
             return new LoginUser(user, list);
        }
        return new LoginUser(user, List.of());
    }

    @Override
    public ResponseResult<UserDetailVo> getUserInfo(Long userId) {
        if(userId==null)return ResponseResult.failure(RespEnum.NOT_LOGIN.getCode(),RespEnum.NOT_LOGIN.getMsg());
        User userDto = userMapper.selectById(userId);
        UserDetailVo ret = userDto.asViewObject(UserDetailVo.class);
        return ResponseResult.success(ret);
    }

    /**
     * 通过邮件或者用户名查找账户
     * @param username
     * @return
     */
    private User findAccountByNameOrEmail(String username){
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, username);
        return this.userMapper.selectOne(wrapper);
    }
    /**
     * 判断用户名或邮箱是否已存在
     *
     * @param username 用户名
     * @param email    邮箱
     * @return boolean
     */
    private boolean userIsExist(String username, String email) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, username).or().eq(User::getEmail, email);
        return this.userMapper.selectOne(wrapper) != null;
    }
    @Override
    public ResponseResult<Void> register(UserRegisterDto registerDto) {
        if(ObjectUtil.isEmpty(registerDto))
            return ResponseResult.failure();
//        判断用户或邮箱是否存在
        if(userIsExist(registerDto.getUsername(),registerDto.getEmail()))return ResponseResult.failure(RespEnum.USERNAME_OR_EMAIL_EXIST.getCode(),RespEnum.USERNAME_OR_EMAIL_EXIST.getMsg());
        // 获取注册ip地址
        String ipAddr = IpUtil.getIpAddr(SecurityUtil.getCurrentHttpRequest());
        if (IpUtil.isUnknown(ipAddr)) {
            ipAddr = IpUtil.getHostIp();
        }
        String addressByIP = AddressUtil.getRealAddressByIP(ipAddr);
        //        密码加密
        String enPassword = passwordEncoder.encode(registerDto.getPassword());
        User user = User.builder()
                .id(null)
                .nickname(registerDto.getUsername())
                .username(registerDto.getUsername())
                .password(enPassword)
                .registerType(0)
                .registerIp(ipAddr)
                .registerAddress(addressByIP)
                .isDeleted(UserConstant.DEFAULT_STATUS)
                .gender(UserConstant.DEFAULT_GENDER)
                .avatar(UserConstant.DEFAULT_AVATAR)
                .intro(UserConstant.DEFAULT_INTRODUCTION)
                .email(registerDto.getEmail())
                .loginTime(new Date()).build();
        if (this.save(user)) {
            // 删除验证码
            redisCache.deleteObject(RedisConstant.VERIFY_CODE + RedisConstant.REGISTER + RedisConstant.SEPARATOR + registerDto.getEmail());
            return ResponseResult.success();
        } else {
            return ResponseResult.failure();
        }
    }

    @Override
    public ResponseResult<Void> resetPassword(UserResetDto userResetDto) {
        // 校验验证码
        ResponseResult<Void> verifyCode = verifyCode(userResetDto.getEmail(), userResetDto.getCode(), RedisConstant.RESET);
        if (verifyCode != null) return verifyCode;
        String password = passwordEncoder.encode(userResetDto.getPassword());
        User user = User.builder().password(password).build();
        if (this.update(user, new LambdaQueryWrapper<User>().eq(User::getEmail, userResetDto.getEmail()))) {
            // 删除验证码
            redisCache.deleteObject(RedisConstant.VERIFY_CODE + RedisConstant.RESET + RedisConstant.SEPARATOR + userResetDto.getEmail());
            return ResponseResult.success();
        } else {
            return ResponseResult.failure();
        }
    }

    @Override
    public ResponseResult<Void> resetConfirm(UserResetDto userResetDto) {
        ResponseResult<Void> verifyCode =verifyCode(userResetDto.getEmail(),userResetDto.getCode(),RedisConstant.RESET);
        if (verifyCode != null) return verifyCode;
        return ResponseResult.success();
    }
    private ResponseResult<Void> verifyCode(String email, String code, String type){
        String redisCode = redisCache.getCacheObject(RedisConstant.VERIFY_CODE + type + RedisConstant.SEPARATOR + email);
        if (redisCode == null)
            return ResponseResult.failure(RespEnum.VERIFY_CODE_ERROR.getCode(), RespConstant.VERIFY_CODE_NULL_MSG);
        if (!redisCode.equals(code))
            return ResponseResult.failure(RespEnum.VERIFY_CODE_ERROR.getCode(), RespEnum.VERIFY_CODE_ERROR.getMsg());
        return null;
    }

}




