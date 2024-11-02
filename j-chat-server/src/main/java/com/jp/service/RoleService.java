package com.jp.service;

import com.jp.domain.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 12607
* @description 针对表【j_role】的数据库操作Service
* @createDate 2024-10-30 05:13:34
*/
public interface RoleService extends IService<Role> {
    Role getRoleByUserId(Long userId);
}
