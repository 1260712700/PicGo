package com.jp.mapper;

import com.jp.domain.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 12607
* @description 针对表【j_comment】的数据库操作Mapper
* @createDate 2024-10-30 05:13:34
* @Entity com.jp.domain.Comment
*/
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}




