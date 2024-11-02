package com.jp.mapper;

import com.jp.domain.entity.Follow;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 12607
* @description 针对表【j_follow】的数据库操作Mapper
* @createDate 2024-10-30 22:19:24
* @Entity com.jp.domain.Follow
*/
@Mapper
public interface FollowMapper extends BaseMapper<Follow> {

}




