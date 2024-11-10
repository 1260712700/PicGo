package com.jp.domain.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.jp.utils.RedisCache;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Data
public class LoginUser implements UserDetails {

    @Resource
    private RedisCache redisCache;

    private User user;

    // 存储权限信息
    private List<String> permissions;

    // 存储 Spring Security 所需要的权限信息的集合
    @JSONField(serialize = false)
    private List<SimpleGrantedAuthority> authorities;

    public LoginUser(User user) {
        this.user = user;
        this.permissions = Collections.emptyList(); // 初始化为空列表
        this.authorities = Collections.emptyList(); // 初始化为空列表
    }

    public LoginUser(User user, List<String> permissions) {
        this.user = user;
        this.permissions = permissions;
        this.authorities = convertToAuthorities(permissions); // 初始化权限
    }

    /**
     * 获取权限
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authorities == null) {
            authorities = convertToAuthorities(permissions);
        }
        return authorities;
    }

    private List<SimpleGrantedAuthority> convertToAuthorities(List<String> permissions) {
        if (permissions == null || permissions.isEmpty()) {
            return Collections.emptyList();
        }
        return permissions.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getPassword(); // 确保密码已经加密
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        // 可以根据业务逻辑动态检查用户是否过期
        return true; // TODO: 需根据实际情况更新
    }

    @Override
    public boolean isAccountNonLocked() {
        // 可以根据业务逻辑动态检查用户是否被锁定
        return true; // TODO: 需根据实际情况更新
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 可以根据业务逻辑动态检查凭证是否过期
        return true; // TODO: 需根据实际情况更新
    }

    @Override
    public boolean isEnabled() {
        // 可以根据业务逻辑动态检查用户是否启用
        return true; // TODO: 需根据实际情况更新
    }
}