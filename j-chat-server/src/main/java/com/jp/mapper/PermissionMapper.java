package com.jp.mapper;

import com.jp.domain.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 12607
* @description 针对表【j_permission】的数据库操作Mapper
* @createDate 2024-10-31 03:34:06
* @Entity com.jp.domain.Permission
*/
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

}




