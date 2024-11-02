package com.jp.mapper;

import com.jp.domain.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 12607
* @description 针对表【j_category】的数据库操作Mapper
* @createDate 2024-10-30 05:13:34
* @Entity com.jp.domain.Category
*/
@Mapper

public interface CategoryMapper extends BaseMapper<Category> {

}




