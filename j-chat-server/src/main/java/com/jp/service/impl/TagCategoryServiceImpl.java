package com.jp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jp.domain.entity.TagCategory;
import com.jp.service.TagCategoryService;
import com.jp.mapper.TagCategoryMapper;
import org.springframework.stereotype.Service;

/**
* @author 12607
* @description 针对表【j_tag_category】的数据库操作Service实现
* @createDate 2024-11-05 08:42:48
*/
@Service
public class TagCategoryServiceImpl extends ServiceImpl<TagCategoryMapper, TagCategory>
    implements TagCategoryService{

}




