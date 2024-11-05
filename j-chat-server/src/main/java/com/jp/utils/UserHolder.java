package com.jp.utils;

import com.jp.domain.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @Author JP
 * @Date 2024/11/5 7:37
 */
public class UserHolder {
    private static final ThreadLocal<User> userHolder = ThreadLocal.withInitial(User::new);
    // 设置用户
    public static void setUser(User user) {
        userHolder.set(user);
    }

    // 获取用户
    public static User getUser() {
        return userHolder.get();
    }

    // 清除用户
    public static void clearUser() {
        userHolder.remove();
    }
}
