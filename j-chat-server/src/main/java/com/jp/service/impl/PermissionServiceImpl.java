package com.jp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jp.domain.entity.Permission;
import com.jp.service.PermissionService;
import com.jp.mapper.PermissionMapper;
import org.springframework.stereotype.Service;

/**
* @author 12607
* @description 针对表【j_permission】的数据库操作Service实现
* @createDate 2024-10-31 03:34:06
*/
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission>
    implements PermissionService{

}




