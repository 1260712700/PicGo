package com.jp.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author JP
 * @Date 2024/11/1 13:29
 */
@Getter
@AllArgsConstructor
public enum RoleEnum {
    Role_STATUS_ARTICLE(0, "状态：正常"),
    ROLE_STATUS_ARTICLE(1, "状态：停用");

    // 类型
    private final Integer status;
    // 描述
    private final String desc;
}
