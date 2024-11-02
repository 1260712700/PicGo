package com.jp.mapper;

import com.jp.domain.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 12607
* @description 针对表【j_role】的数据库操作Mapper
* @createDate 2024-10-30 05:13:34
* @Entity com.jp.domain.Role
*/
@Mapper

public interface RoleMapper extends BaseMapper<Role> {

}




