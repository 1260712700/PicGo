package com.jp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jp.domain.entity.UserRole;
import com.jp.service.UserRoleService;
import com.jp.mapper.UserRoleMapper;
import org.springframework.stereotype.Service;

/**
* @author 12607
* @description 针对表【j_user_role】的数据库操作Service实现
* @createDate 2024-10-30 05:13:35
*/
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>
    implements UserRoleService{

}




