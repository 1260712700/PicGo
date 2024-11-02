package com.jp.mapper;

import com.jp.domain.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 12607
* @description 针对表【j_user】的数据库操作Mapper
* @createDate 2024-10-30 05:13:34
* @Entity com.jp.domain.User
*/
@Mapper

public interface UserMapper extends BaseMapper<User> {

}




