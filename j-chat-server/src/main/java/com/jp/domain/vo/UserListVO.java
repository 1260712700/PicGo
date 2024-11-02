package com.jp.domain.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Author JP
 * @Date 2024/11/2 1:37
 */
@Data

public class UserListVO {
        //用户id
        private Long id;
        //用户名
        private String username;
        //用户头像
        private String avatar;
        //用户邮箱
        private String email;
        //用户注册方式(1邮箱/姓名 2Gitee 3Github)
        private Integer registerType;
        // 登录地址
        private String loginAddress;
        //是否禁用 0 否 1 是
        private Integer isDisable;
        //用户创建时间
        private Date createTime;
    }
