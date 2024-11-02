package com.jp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jp.domain.entity.Role;
import com.jp.domain.entity.UserRole;
import com.jp.mapper.UserRoleMapper;
import com.jp.service.RoleService;
import com.jp.mapper.RoleMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
* @author 12607
* @description 针对表【j_role】的数据库操作Service实现
* @createDate 2024-10-30 05:13:34
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private UserRoleMapper userRoleMapper;

    /**
     * 根据用户id获取用户角色
     * @param userId 用户id
     * @return 用户角色
     */
    @Override
    public Role getRoleByUserId(Long userId) {
        LambdaQueryWrapper<Role> roleLqw=new LambdaQueryWrapper<>();
        LambdaQueryWrapper<UserRole>userRoleLqw=new LambdaQueryWrapper<>();
        userRoleLqw.eq(UserRole::getUserId,userId);
        UserRole userRoleDto = userRoleMapper.selectOne(userRoleLqw);
        String roleId = userRoleDto.getRoleId();
        roleLqw.eq(Role::getId,roleId);
        return roleMapper.selectOne(roleLqw);
    }
}




